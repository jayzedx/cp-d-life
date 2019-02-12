package com.mdc.cpfit.screen

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mdc.cpfit.R
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.util.ScreenUnit
import com.mdc.cpfit.util.sharepreferrent.ConfigServer
import kotlinx.android.synthetic.main.progress_simple.*
import kotlinx.android.synthetic.main.sc_webview.*
import kotlinx.android.synthetic.main.toolbar_back.*


class WebViewScreen : ScreenUnit() {

    val TAG = WebViewScreen::class.java.simpleName
    var rootView: View? = null
    var url: String? = null


    companion object {
        fun newInstance(url: String?): WebViewScreen {
            val fragment = WebViewScreen()
            val args = Bundle()
            args.putString(MsgProperties.ARGUMENT, url)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(WebViewScreen::class.simpleName.toString(), rootView)
        setValue()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_webview, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
        setToolbar()

        url = args?.getString(MsgProperties.ARGUMENT)
        url?.apply {
            when {
                ConfigServer.instance.agreement?.contains(this)!! -> toolbarTitle.text = getString(R.string.webview_policy)
            }
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    onProgress.visibility = View.GONE
                }
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    onProgress.visibility = View.VISIBLE
                }
                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    viewErrorWebview.visibility = View.VISIBLE
                    onProgress.visibility = View.GONE
                }
            }
            webView.settings.javaScriptEnabled = true

        }.also {
            it?.run {
                webView.loadUrl(MsgProperties.GOOGLEDOC_PATH + url)
            } ?: run {
                viewErrorWebview.visibility = View.VISIBLE
            }
        }

    }

    private fun setToolbar() {
        backMenu?.setOnClickListener {
            goback(false)
        }
    }

}