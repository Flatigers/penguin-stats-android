package org.penguin_stats.android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentToolBinding

class ToolFragment : Fragment() {
    private lateinit var binding: FragmentToolBinding
    private val viewModel by lazy { ViewModelProvider(this)[ToolViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tool, container, false)
        binding.tool = viewModel
        return binding.root
    }

}