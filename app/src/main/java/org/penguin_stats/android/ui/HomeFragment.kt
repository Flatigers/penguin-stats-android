package org.penguin_stats.android.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.noties.markwon.Markwon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.penguin_stats.android.R
import org.penguin_stats.android.app.AppConfig
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.databinding.FragmentHomeBinding
import org.penguin_stats.android.ui.secondary.SecondaryActivity
import org.penguin_stats.android.util.codeFromI18N


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.home = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val markWon = Markwon.create(requireActivity())

        if (Repository.isNoticeSaved()) {
            MainScope().launch(Dispatchers.IO) {
                val notice = Repository.readNotice()
                notice[0].run {
                    withContext(Dispatchers.Main) {
                        markWon.setMarkdown(
                            binding.homeNotice.homeNoticeT,
                            codeFromI18N(content_i18n, existence)
                                .replace("\n", "\n\n")
                        )
                    }
                }
            }
        }

        binding.homeSwipeRefresher.run {
            setOnRefreshListener {
                MainScope().launch(Dispatchers.IO) {
                    Repository.saveNotice()
                    val notice = Repository.readNotice()[0].run {
                        codeFromI18N(content_i18n, existence).replace("\n", "\n\n")
                    }
                    withContext(Dispatchers.Main) {
                        isRefreshing = false
                        markWon.setMarkdown(binding.homeNotice.homeNoticeT, notice)
                    }
                }
            }
        }

        val drop = binding.homeDrop
        drop.homeDropStage.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.home_drop_stage)
        }
        drop.homeDropItem.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.home_drop_item)
        }

        val report = binding.homeReport
        report.homeReportStage.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.home_report_stage)
        }
        report.homeReportReco.setOnClickListener {
            SecondaryActivity.start(requireActivity(), R.string.home_report_reco)
        }

        binding.homeOpenBrowser.setOnClickListener {
            Intent().apply {
                action = Intent.ACTION_VIEW
                addCategory(Intent.CATEGORY_BROWSABLE)
                data = Uri.parse("https://penguin-stats." + AppConfig.getDomain())
                requireActivity().startActivity(this)
            }
        }
    }


}