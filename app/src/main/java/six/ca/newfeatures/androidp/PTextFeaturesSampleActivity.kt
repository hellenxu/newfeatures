package six.ca.newfeatures.androidp

import android.app.Activity
import android.os.Bundle
import android.text.SpannableString
import android.text.util.Linkify
import android.view.textclassifier.TextClassifier
import android.view.textclassifier.TextLinks
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import kotlinx.android.synthetic.main.act_text_features.*
import six.ca.newfeatures.R
import java.lang.ref.WeakReference
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-09-07.
 */
class PTextFeaturesSampleActivity: Activity() {
    private val textClassifier: TextClassifier by lazy {
        object: TextClassifier{}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_text_features)

        asyncSetText(computedText, getString(R.string.long_string), executor = Executors.newSingleThreadExecutor())
        generateLinks(smartLinkifyText, executor = Executors.newSingleThreadExecutor())

    }

    private fun asyncSetText(textSource: TextView, longString: String, executor: Executor) {
        val params = TextViewCompat.getTextMetricsParams(textSource)
        val textRef = WeakReference<TextView>(textSource)
        executor.execute {
            val textViewOut = textRef.get()
            if(textViewOut == null) {
                return@execute
            }

            val text = PrecomputedTextCompat.create(longString, params)
            textViewOut.post {
                textSource.text = text
            }
        }
    }

    private fun generateLinks(textSource: TextView, executor: Executor) {
        val textSpannable = SpannableString(textSource.text)
        val request = TextLinks.Request.Builder(textSpannable).build()
        val ref = WeakReference<TextView>(textSource)

        executor.execute {
            val textView = ref.get() ?: return@execute

            val mask = textClassifier.generateLinks(request).apply(textSpannable, TextLinks.APPLY_STRATEGY_REPLACE, null)
            Linkify.addLinks(textSpannable, Linkify.EMAIL_ADDRESSES)

            textView.post {
                textView.text = textSpannable
            }
        }
    }
}