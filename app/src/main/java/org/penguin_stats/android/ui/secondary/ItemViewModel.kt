package org.penguin_stats.android.ui.secondary

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.penguin_stats.android.data.ResponseItems

class ItemViewModel : ViewModel() {
    private var items: ObservableField<List<ResponseItems>> = ObservableField()
    private var filteredItems: ObservableField<List<ResponseItems>> = ObservableField()

    fun setItems(mItems: List<ResponseItems>) {
        items.set(mItems)
    }

    fun setFilteredItems(mItems: List<ResponseItems>) {
        filteredItems.set(mItems)
    }

    fun getItems(): List<ResponseItems> {
        return items.get() ?: listOf()
    }

    fun getFilteredItems(): List<ResponseItems> {
        return filteredItems.get() ?: listOf()
    }
}