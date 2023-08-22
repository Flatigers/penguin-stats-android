package org.penguin_stats.android.ui.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.noties.markwon.Markwon
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentSecMarkdownBinding

class SecMarkdownPage : Fragment() {
    private lateinit var binding: FragmentSecMarkdownBinding
    private var markText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        markText = arguments?.getString("mark") ?: "null"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sec_markdown, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mark = Markwon.create(requireActivity())
        mark.setMarkdown(binding.secMarkdownViewer, markText)
    }

    companion object {

        @JvmStatic
        fun new(markdown: String) =
            SecMarkdownPage().apply {
                arguments = Bundle().apply {
                    putString("mark", markdown)
                }
            }
    }
}