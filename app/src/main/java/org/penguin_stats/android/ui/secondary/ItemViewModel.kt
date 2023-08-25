package org.penguin_stats.android.ui.secondary

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.penguin_stats.android.data.ItemUI

class ItemViewModel : ViewModel() {
    var items: ObservableField<List<ItemUI>> = ObservableField()
    var filteredItems: ObservableField<List<ItemUI>> = ObservableField()
}