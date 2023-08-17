package org.penguin_stats.android.ui.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentSecSettingsBinding


class SettingsPage : Fragment() {
    private lateinit var binding: FragmentSecSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sec_settings, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sIsNight.setOnCheckedChangeListener { _, isChecked -> }
        binding.sMirror.setOnCheckedChangeListener { _, isChecked -> }
        binding.sServerGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.s_server_cn -> {}
                R.id.s_server_us -> {}
                R.id.s_server_jp -> {}
                R.id.s_server_kr -> {}
            }
        }
    }

    companion object {

        @JvmStatic
        fun new() = SettingsPage()
    }
}