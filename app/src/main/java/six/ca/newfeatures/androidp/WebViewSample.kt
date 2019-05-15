package six.ca.newfeatures.androidp

import android.app.Activity
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView
import kotlinx.android.synthetic.main.act_wv.*
import six.ca.newfeatures.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-05-14.
 */
class WebViewSample : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_wv)

        wv.settings.safeBrowsingEnabled = true
        WebView.startSafeBrowsing(this) { value ->
            if (value == true) println("xxl-safe browsing on") else println("xxl-safe browsing off")
        }

        //set white list
        val array = ArrayList<String>()
        array.add("abc.com")
        WebView.setSafeBrowsingWhitelist(array) { }

    }
}