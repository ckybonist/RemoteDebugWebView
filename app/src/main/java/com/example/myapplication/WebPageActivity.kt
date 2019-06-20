package com.example.myapplication

import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.os.Build
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_web_page.*

class WebPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_page)
        setSupportActionBar(toolbar)

        enableChromeRemoteDebug()

        val props: Bundle? = intent.extras
        if (props != null) {
            val myWebView: WebView = findViewById(R.id.webView)
            myWebView.settings.javaScriptEnabled = true

            val url = props.getString("url")
            myWebView.loadUrl(url)
        }
    }

    private fun enableChromeRemoteDebug() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
}
