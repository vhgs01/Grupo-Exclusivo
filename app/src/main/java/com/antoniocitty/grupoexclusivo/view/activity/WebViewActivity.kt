package com.antoniocitty.grupoexclusivo.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.antoniocitty.grupoexclusivo.databinding.ActivityWebViewBinding


class WebViewActivity : AppCompatActivity() {

    companion object {
        private const val WEBVIEW_TO_OPEN = "webview_to_open"
        private const val HANDOUT = "handout"
        private const val PURCHASE = "purchase"
        private const val LOGGED_AREA = "logged_area"
        private const val BASE_URL = "https://docs.google.com/gview?embedded=true&url="
        private const val HANDOUT_URL = "https://efd7713d-3e88-4c91-b4e0-6f1bb1385445.filesusr.com/ugd/980d9f_5547331aa4a44ca88de5cd9411c02306.pdf"
        private const val PURCHASE_URL = "https://go.hotmart.com/W61107558X"
        private const val LOGGED_AREA_URL = "https://grupoexclusivo.com"
    }

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureBackPressed()
        configureWebview(intent.getStringExtra(WEBVIEW_TO_OPEN))
    }

    private fun configureBackPressed() = with(binding) {
        onBackPressedDispatcher.addCallback(
            this@WebViewActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webview.canGoBack()) webview.goBack() else finish()
                }
            })
    }

//    override fun onBackPressed() = with(binding) {
//        if (webview.canGoBack()) webview.goBack() else super.onBackPressed()
//    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebview(toOpen: String?) = with(binding) {
        if (toOpen != null) {
            webview.webViewClient = WebViewClient()
            webview.settings.setSupportZoom(true)
            webview.settings.javaScriptEnabled = true
            webview.settings.domStorageEnabled = true

            when (toOpen) {
                HANDOUT -> {
                    webview.loadUrl("$BASE_URL$HANDOUT_URL")
                }

                PURCHASE -> {
                    webview.loadUrl(PURCHASE_URL)
                }

                LOGGED_AREA -> {
                    webview.loadUrl(LOGGED_AREA_URL)
                }
            }
        }
    }
}