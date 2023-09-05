package org.penguin_stats.android.ui.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentDetailStageBinding

class DetailStageFragment : Fragment() {
    private lateinit var binding: FragmentDetailStageBinding
    private val viewModel by lazy { ViewModelProvider(this)[DetailedStageViewModel::class.java] }
    private lateinit var mStageId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        arguments?.apply {
            mStageId = getString("stageId") ?: ""
        }
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_stage, container, false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            setStageId(mStageId)
            setSanity(getStage().apCost)
            setTime(getStage().minClearTime ?: 0)
            setCount()
            setInterval()
        }


    }

    companion object {
        @JvmStatic
        fun new(stageId: String) = DetailStageFragment().apply {
            arguments = Bundle().apply {
                putString("stageId", stageId)
            }
        }
    }
}