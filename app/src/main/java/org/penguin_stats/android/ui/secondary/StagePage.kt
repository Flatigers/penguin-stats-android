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

        // init UI data to be showed
        MainScope().launch(Dispatchers.IO) {
            val zones = Repository.readAllZones()
            viewModel.setZones(zones)
            viewModel.setFilteredZones(zones)
            withContext(Dispatchers.Main) {
                setAdapter()
            }
        }

        // set adapter for SwipeRefreshLayout
        binding.stageRefresher.run {
            setOnRefreshListener {
                MainScope().launch(Dispatchers.IO) {
                    Repository.saveZones()
                    Repository.saveStages()
                    val zones = Repository.readAllZones()
                    viewModel.setZones(zones)
                    viewModel.setFilteredZones(zones)
                    withContext(Dispatchers.Main) {
                        isRefreshing = false
                        binding.stageChipGroup.check(R.id.stage_chip_all)
                        setAdapter()
                    }
                }
            }
        }

        // set check-adapter for [ChipGroup]
        // change data by filter [it.type]
        binding.stageChipGroup.setOnCheckedStateChangeListener { group, _ ->
            MainScope().launch(Dispatchers.IO) {
                val zones = viewModel.getZones()
                viewModel.setFilteredZones(
                    zones.filter {
                        filterI18NExistence(it.existence) && when (group.checkedChipId) {
                            R.id.stage_chip_all -> true
                            R.id.stage_chip_e_open -> it.type == "ACTIVITY" && filterI18NTime(it.existence)
                            R.id.stage_chip_main -> it.type == "MAINLINE"
                            R.id.stage_chip_ss -> it.type == "ACTIVITY_PERMANENT"
                            R.id.stage_chip_e_close -> it.type == "ACTIVITY" && !filterI18NTime(it.existence)
                            R.id.stage_chip_supply -> it.type == "WEEKLY"
                            R.id.stage_chip_recruit -> it.type == "RECRUIT"
                            R.id.stage_chip_gachabox -> it.type == "GACHABOX"
                            else -> false
                        }
                    })
                withContext(Dispatchers.Main) {
                    setAdapter()
                }
            }
        }

        val layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.stageRecycler.layoutManager = layoutManager
        if (type == REPORT) typeReportFunc()
    }

    // function to init a new Adapter of [StageZonesAdapter]
    // with newed data for RecyclerView
    private fun setAdapter() {
        viewModel.setAdapter(
            StageZonesAdapter(requireActivity(), type, viewModel.getFilteredZones())
        )
        binding.stageRecycler.adapter = viewModel.getAdapter()
    }

    // fun to remove [Chip Close] & [Chip Recruit]
    // called when this.REPORT
    private fun typeReportFunc() {
        binding.stageChipEClose.visibility = View.GONE
        binding.stageChipRecruit.visibility = View.GONE
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