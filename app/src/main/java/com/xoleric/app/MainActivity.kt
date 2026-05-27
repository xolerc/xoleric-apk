package com.xoleric.app

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.app.Activity

class MainActivity : Activity() {
    private lateinit var webView: WebView
    private val appUrl = "https://xolerc.github.io/xoleric-parfulyo/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this)
        setContentView(webView)

        try {
            webView.apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.databaseEnabled = true
                settings.allowFileAccess = false
                settings.allowContentAccess = false
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.builtInZoomControls = false
                settings.displayZoomControls = false
                settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_NEVER_ALLOW
                settings.cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
                setInitialScale(100)

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        request: WebResourceRequest
                    ): Boolean {
                        return false
                    }

                    override fun onReceivedError(
                        view: WebView,
                        errorCode: Int,
                        description: String,
                        failingUrl: String
                    ) {
                        loadDataWithBaseURL(null,
                            "<html><body style='background:#05080f;color:#888;display:flex;align-items:center;justify-content:center;height:100vh;margin:0;font-family:sans-serif;text-align:center;padding:20px'>" +
                            "<div><h2 style='color:#4d7cff;margin-bottom:8px'>XOLERIC</h2>" +
                            "<p style='font-size:14px;color:#666'>Yuklashda xatolik<br><small>$description</small></p>" +
                            "<button onclick='location.reload()' style='margin-top:16px;padding:10px 24px;border:1px solid #333;border-radius:8px;background:transparent;color:#888;font-size:13px'>Qayta urinish</button></div></body></html>",
                            "text/html", "UTF-8", null)
                    }
                }

                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, progress: Int) {
                        if (progress == 100) {
                            // loading complete
                        }
                    }
                }

                if (savedInstanceState != null) {
                    restoreState(savedInstanceState)
                } else {
                    loadUrl(appUrl)
                }
            }
        } catch (e: Exception) {
            webView.loadDataWithBaseURL(null,
                "<html><body style='background:#05080f;color:#888;display:flex;align-items:center;justify-content:center;height:100vh;margin:0;font-family:sans-serif;text-align:center;padding:20px'>" +
                "<div><h2 style='color:#f05050;margin-bottom:8px'>Xatolik</h2>" +
                "<p style='font-size:13px;color:#666'>" + e.message + "</p></div></body></html>",
                "text/html", "UTF-8", null)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::webView.isInitialized) webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}
