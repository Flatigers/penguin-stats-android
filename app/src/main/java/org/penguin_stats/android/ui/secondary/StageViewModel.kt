package org.penguin_stats.android.ui.secondary

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.data.ZoneUI

class StageViewModel : ViewModel() {
    var zones: ObservableField<List<ZoneUI>> = ObservableField()
    var filteredZones: ObservableField<List<ZoneUI>> = ObservableField()
    val stages = Repository.readStagesUI()
}