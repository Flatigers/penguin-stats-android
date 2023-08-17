package org.penguin_stats.android.ui.secondary

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentSecWebViewerBinding


class SecWebViewerPage : Fragment() {
    private lateinit var binding: FragmentSecWebViewerBinding
    private lateinit var uri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            uri = getString("uri") ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sec_web_viewer, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.secWebRefresher.setOnRefreshListener {
            binding.secWebViewer.reload()
        }
        binding.secWebViewer.apply {
            settings.run {
                javaScriptEnabled = true
                databaseEnabled = true
                domStorageEnabled = true
                allowContentAccess = true
            }
            webViewClient = WebClient()
            loadUrl(uri)
        }
        binding.secWebRefresher.post { binding.secWebRefresher.isRefreshing = true }

    }

    inner class WebClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.secWebRefresher.isRefreshing = false
        }
    }

    companion object {
        @JvmStatic
        fun new(uri: String) = SecWebViewerPage().apply {
            arguments = Bundle().apply {
                putString("uri", uri)
            }
        }
    }

}