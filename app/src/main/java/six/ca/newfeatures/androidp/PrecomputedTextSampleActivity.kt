package six.ca.newfeatures.androidp

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import kotlinx.android.synthetic.main.act_precomputed.*
import six.ca.newfeatures.R
import java.lang.ref.WeakReference
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-09-07.
 */
class PrecomputedTextSampleActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_precomputed)
        asyncSetText(computedText, getString(R.string.long_string), executor = Executors.newSingleThreadExecutor())
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
}