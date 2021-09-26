package br.com.kaz

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {

    companion object {
        private const val WEBVIEW_TO_OPEN = "webview_to_open"
        private const val HANDOUT = "handout"
        private const val PURCHASE = "purchase"
        private const val BASE_URL = "https://docs.google.com/gview?embedded=true&url="
        private const val HANDOUT_URL = "https://antoniocitty.com/Apostila.pdf"
        private const val PURCHASE_URL = "https://encitty.kpages.online/grupo1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        configureWebview(intent.getStringExtra(WEBVIEW_TO_OPEN))
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) webview.goBack() else super.onBackPressed()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebview(toOpen: String?) {
        if (toOpen != null) {
            webview.webViewClient = WebViewClient()
            webview.settings.setSupportZoom(true)
            webview.settings.javaScriptEnabled = true
            webview.settings.domStorageEnabled = true

            if (toOpen == HANDOUT) {
                webview.loadUrl("$BASE_URL$HANDOUT_URL")
            } else if (toOpen == PURCHASE) {
                webview.loadUrl(PURCHASE_URL)
            }
        }
    }
}