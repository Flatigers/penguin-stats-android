package org.penguin_stats.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.FragmentAboutBinding
import org.penguin_stats.android.ui.secondary.SecondaryActivity

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

        val dev = binding.aboutDev
        dev.aboutDev.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_dev)
        }
        dev.aboutDonate.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_donate)
        }
        dev.aboutContact.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_contact)
        }
        dev.aboutContactLicense.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_content_license)
        }

        val penguin = binding.aboutPenguin
        penguin.aboutMembers.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_members)
        }
        penguin.aboutChangelog.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_changelog)
        }
        penguin.aboutLinks.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_links)
        }
        penguin.aboutCredit.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_credit)
        }

        val app = binding.aboutApp
        app.aboutSettings.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_settings)
        }
        app.aboutOpensource.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_opensource)
        }
        app.aboutPrivacy.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.about_privacy)
        }

    }


}