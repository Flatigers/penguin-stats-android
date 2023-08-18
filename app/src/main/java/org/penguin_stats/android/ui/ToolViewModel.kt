package org.penguin_stats.android.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.penguin_stats.android.data.TotalStatsUI

class ToolViewModel : ViewModel() {
    var totalStats: ObservableField<TotalStatsUI> = ObservableField(
        TotalStatsUI(
            "0", "0", "0"
        )
    )
}