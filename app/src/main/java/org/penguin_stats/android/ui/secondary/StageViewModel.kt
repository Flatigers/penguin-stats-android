package org.penguin_stats.android.ui.secondary

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.penguin_stats.android.data.StageUI
import org.penguin_stats.android.data.ZoneUI

class StageViewModel : ViewModel() {
    private val zones: ObservableField<List<ZoneUI>> = ObservableField()
    private val filteredZones: ObservableField<List<ZoneUI>> = ObservableField()
    private val stages: ObservableField<List<StageUI>> = ObservableField()
    private var adapter: StageZonesAdapter? = null

    fun setZones(mZones: List<ZoneUI>) {
        zones.set(mZones)
    }

    fun setFilteredZones(mZones: List<ZoneUI>) {
        filteredZones.set(mZones)
    }

    fun setStages(mStages: List<StageUI>) {
        stages.set(mStages)
    }

    fun setAdapter(mAdapter: StageZonesAdapter) {
        adapter = mAdapter
    }

    fun getZones(): List<ZoneUI> {
        return zones.get() ?: listOf()
    }

    fun getFilteredZones(): List<ZoneUI> {
        return filteredZones.get() ?: listOf()
    }

    fun getStages(): List<StageUI> {
        return stages.get() ?: listOf()
    }

    fun getAdapter(): StageZonesAdapter? {
        return adapter
    }
}