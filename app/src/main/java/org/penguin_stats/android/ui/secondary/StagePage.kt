package org.penguin_stats.android.ui.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.penguin_stats.android.R
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.databinding.FragmentSecStageBinding
import org.penguin_stats.android.util.filterI18NExistence
import org.penguin_stats.android.util.filterI18NTime


class StagePage : Fragment() {
    private lateinit var binding: FragmentSecStageBinding
    private val viewModel by lazy { ViewModelProvider(this)[StageViewModel::class.java] }
    private var type: Int = QUERY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getInt("type")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sec_stage, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Repository.isZonesUISaved() && Repository.isStagesUISaved()) {
            viewModel.zones.set(Repository.readZonesUI())
        }

        binding.stageRefresher.run {
            setOnRefreshListener {
                MainScope().launch(Dispatchers.IO) {
                    Repository.saveZones()
                    Repository.saveStages()
                    viewModel.zones.set(Repository.readZonesUI())
                    withContext(Dispatchers.Main) {
                        isRefreshing = false
                        binding.stageChipGroup.check(R.id.stage_chip_all)
                    }
                }
            }
        }

        binding.stageChipGroup.setOnCheckedStateChangeListener { group, _ ->
            val zones = viewModel.zones.get() ?: listOf()
            viewModel.filteredZones.set(zones.filter {
                filterI18NExistence(it.existence) && when (group.checkedChipId) {
                    R.id.stage_chip_all -> true
                    R.id.stage_chip_e_open -> it.type == "ACTIVITY" && filterI18NTime(it.existence)
                    R.id.stage_chip_main -> it.type == "MAINLINE"
                    R.id.stage_chip_ss -> it.type == "ACTIVITY_PERMANENT"
                    R.id.stage_chip_e_close -> it.type == "ACTIVITY" && !filterI18NTime(it.existence)
                    R.id.stage_chip_supply -> it.type == "WEEKLY"
                    R.id.stage_chip_recurit -> it.type == "RECRUIT"
                    R.id.stage_chip_gachabox -> it.type == "GACHABOX"
                    else -> true
                }
            })
            val adapter =
                StageZonesAdapter(
                    requireActivity(), type,
                    viewModel.filteredZones.get() ?: listOf(), viewModel.stages
                )
            binding.stageRecycler.adapter = adapter
        }

        val layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.stageRecycler.layoutManager = layoutManager
        val adapter = StageZonesAdapter(
            requireActivity(), type,
            viewModel.zones.get() ?: listOf(), viewModel.stages
        )
        binding.stageRecycler.adapter = adapter


        if (type == REPORT) typeReportFunc()
    }

    private fun typeReportFunc() {
        binding.stageChipEClose.visibility = View.GONE
        binding.stageChipRecurit.visibility = View.GONE
    }


    companion object {
        const val QUERY = 0
        const val REPORT = 1

        @JvmStatic
        fun new(type: Int) = StagePage().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }
}