package org.penguin_stats.android.ui.secondary

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.penguin_stats.android.data.ResponseZones

class StageViewModel : ViewModel() {
    private val zones: ObservableField<List<ResponseZones>> = ObservableField()
    private val filteredZones: ObservableField<List<ResponseZones>> = ObservableField()
    private var adapter: StageZonesAdapter? = null

    fun setZones(mZones: List<ResponseZones>) {
        zones.set(mZones)
    }

    fun setFilteredZones(mZones: List<ResponseZones>) {
        filteredZones.set(mZones)
    }

    fun setAdapter(mAdapter: StageZonesAdapter) {
        adapter = mAdapter
    }

    fun getZones(): List<ResponseZones> {
        return zones.get() ?: listOf()
    }

    fun getFilteredZones(): List<ResponseZones> {
        return filteredZones.get() ?: listOf()
    }

    fun getAdapter(): StageZonesAdapter? {
        return adapter
    }
}