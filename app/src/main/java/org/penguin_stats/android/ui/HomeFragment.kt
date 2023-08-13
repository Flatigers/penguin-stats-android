package org.penguin_stats.android.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.home = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO
        val drop = binding.homeDrop
        drop.homeDropStage.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        drop.homeDropItem.setOnClickListener { TodoInfoPage.start(requireActivity()) }

        val report = binding.homeReport
        report.homeReportStage.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        report.homeReportReco.setOnClickListener { TodoInfoPage.start(requireActivity()) }

        binding.homeOpenBrowser.setOnClickListener {
            Intent().apply {
                action = Intent.ACTION_VIEW
                addCategory(Intent.CATEGORY_BROWSABLE)
                data = Uri.parse("https://penguin-stats.cn/")
                requireActivity().startActivity(this)
            }
        }
    }


}