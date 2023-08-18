package org.penguin_stats.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.penguin_stats.android.R
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.databinding.FragmentToolBinding

class ToolFragment : Fragment() {
    private lateinit var binding: FragmentToolBinding
    private val viewModel by lazy { ViewModelProvider(this)[ToolViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tool, container, false)
        binding.tool = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Repository.isStatsUISaved()) {
            viewModel.totalStats.set(Repository.readStatsUI())
        }

        binding.toolSwipeRefresher.run {
            setOnRefreshListener {
                MainScope().launch(Dispatchers.IO) {
                    Repository.saveStats()
                    viewModel.totalStats.set(Repository.readStatsUI())
                    withContext(Dispatchers.Main) {
                        isRefreshing = false
                    }
                }
            }
        }

    }

}