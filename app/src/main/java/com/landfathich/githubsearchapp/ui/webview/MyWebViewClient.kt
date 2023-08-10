package com.landfathich.githubsearchapp.ui.webview

import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient : WebViewClient() {
    override fun onPageFinished(view: WebView, url: String) {
        CookieManager.getInstance().flush()
    }
}
