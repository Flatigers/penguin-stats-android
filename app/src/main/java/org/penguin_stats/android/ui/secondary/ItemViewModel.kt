package org.penguin_stats.android.ui.secondary

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.penguin_stats.android.data.ItemUI

class ItemViewModel : ViewModel() {
    private var items: ObservableField<List<ItemUI>> = ObservableField()
    private var filteredItems: ObservableField<List<ItemUI>> = ObservableField()

    fun setItems(mItems: List<ItemUI>) {
        items.set(mItems)
    }

    fun setFilteredItems(mItems: List<ItemUI>) {
        filteredItems.set(mItems)
    }

    fun getItems(): List<ItemUI> {
        return items.get() ?: listOf()
    }

    fun getFilteredItems(): List<ItemUI> {
        return filteredItems.get() ?: listOf()
    }
}