package org.penguin_stats.android.ui.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentDetailReportBinding

class DetailReportFragment : Fragment() {
    private lateinit var binding: FragmentDetailReportBinding
    private val viewModel by lazy { ViewModelProvider(this)[DetailedReportViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_report, container, false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun new() = DetailReportFragment()
    }
}