package com.landfathich.githubsearchapp.ui.screen.code

import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.landfathich.githubsearchapp.R
import com.template.webview.MyWebViewClient

class CodeActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)

        link = intent.getStringExtra("link").toString()
        webView = findViewById(R.id.webView)
        initWebView(link)
    }

    private fun initWebView(link: String) {
        webView.webViewClient = MyWebViewClient()
        webView.settings.apply {
            allowFileAccess = true
            javaScriptEnabled = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
        }
        webView.loadUrl(link)
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}