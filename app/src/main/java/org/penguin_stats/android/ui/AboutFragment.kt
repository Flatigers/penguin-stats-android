package org.penguin_stats.android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private val viewModel by lazy { ViewModelProvider(this)[AboutViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        binding.about = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO
        val dev = binding.aboutDev
        dev.aboutDev.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        dev.aboutDonate.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        dev.aboutContact.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        dev.aboutContactLicense.setOnClickListener { TodoInfoPage.start(requireActivity()) }

        val penguin = binding.aboutPenguin
        penguin.aboutMembers.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        penguin.aboutChangelog.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        penguin.aboutLinks.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        penguin.aboutCredit.setOnClickListener { TodoInfoPage.start(requireActivity()) }

        val app = binding.aboutApp
        app.aboutSettings.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        app.aboutOpensource.setOnClickListener { TodoInfoPage.start(requireActivity()) }
        app.aboutPrivacy.setOnClickListener { TodoInfoPage.start(requireActivity()) }

    }


}