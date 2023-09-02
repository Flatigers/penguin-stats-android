package org.penguin_stats.android.ui.secondary

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.penguin_stats.android.R
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.databinding.FragmentSecItemBinding
import org.penguin_stats.android.ui.detailed.DetailedActivity
import org.penguin_stats.android.util.codeFromI18N
import org.penguin_stats.android.util.itemScaledImg

class ItemPage : Fragment() {
    private lateinit var binding: FragmentSecItemBinding
    private val viewModel by lazy { ViewModelProvider(this)[ItemViewModel::class.java] }
    private val img by lazy {
        ResourcesCompat.getDrawable(
            resources,
            R.drawable.sprite_202303070456,
            requireActivity().theme
        )
            ?.toBitmap()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sec_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init UI data to be showed
        if (Repository.isItemsUISaved()) {
            MainScope().launch(Dispatchers.IO) {
                val items = Repository.readItemsUI()
                viewModel.setItems(items)
                viewModel.setFilteredItems(items)
                withContext(Dispatchers.Main) {
                    setItems()
                }
            }
        }

        // set adapter for SwipeRefreshLayout
        binding.itemRefresher.run {
            setOnRefreshListener {
                MainScope().launch(Dispatchers.IO) {
                    Repository.saveItems()
                    val items = Repository.readItemsUI()
                    viewModel.setItems(items)
                    viewModel.setFilteredItems(items)
                    withContext(Dispatchers.Main) {
                        isRefreshing = false
                        binding.itemChipGroup.check(R.id.item_chip_all)
                        setItems()
                    }
                }
            }
        }

        // set check-adapter for [ChipGroup]
        // change data by filter [it.itemType]
        binding.itemChipGroup.setOnCheckedStateChangeListener { group, _ ->
            val items = viewModel.getItems()
            viewModel.setFilteredItems(items.filter {
                when (group.checkedChipId) {
                    R.id.item_chip_all -> true
                    R.id.item_chip_material -> it.itemType == "MATERIAL"
                    R.id.item_chip_card -> it.itemType == "CARD_EXP"
                    R.id.item_chip_chip -> it.itemType == "CHIP"
                    R.id.item_chip_furniture -> it.itemType == "FURN"
                    R.id.item_chip_activity -> it.itemType == "ACTIVITY_ITEM"
                    R.id.item_chip_recruit -> it.itemType == "RECRUIT_TAG"
                    else -> true
                }
            })
            MainScope().launch(Dispatchers.IO) {
                setItems()
            }
        }

    }

    // function to add items[TextView] to show
    private suspend fun setItems() = coroutineScope {
        val items = viewModel.getFilteredItems()
        binding.itemItemGroup.run {
            // Remove views added before
            withContext(Dispatchers.Main) {
                removeAllViews()
            }
            for (i in items) {
                val chip = TextView(requireActivity())
                val name = codeFromI18N(
                    i.name_i18n,
                    i.existence
                )
                // make drawable
                val drawable = itemScaledImg(requireActivity(), i.spriteCoord, img)
                drawable.setBounds(0, 0, 100, 100)
                // remove no-name items
                if (name == "") continue
                chip.run {
                    text = name
                    textSize = 15.5F
                    gravity = Gravity.CENTER
                    setPadding(12)
                    setCompoundDrawables(drawable, null, null, null)
                    compoundDrawablePadding = 8
                    //intent to [DetailedActivity]
                    setOnClickListener {
                        DetailedActivity.start(
                            requireActivity(),
                            DetailedActivity.ITEM,
                            i.itemId
                        )
                    }
                }
                withContext(Dispatchers.Main) {
                    addView(chip)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun new() = ItemPage()
    }
}