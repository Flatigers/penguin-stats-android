package org.penguin_stats.android.ui.secondary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.penguin_stats.android.R
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.data.ResponseZones
import org.penguin_stats.android.ui.detailed.DetailedActivity
import org.penguin_stats.android.util.codeFromI18N

class StageZonesAdapter(
    val context: Context,
    val type: Int,
    private val zones: List<ResponseZones>,
) : RecyclerView.Adapter<StageZonesAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val zoneCard: MaterialCardView = view.findViewById(R.id.sec_stage_item_card)
        val zoneImage: ImageView = view.findViewById(R.id.sec_stage_item_img)
        val zoneText: TextView = view.findViewById(R.id.sec_stage_item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_sec_stage_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zone = zones[position]
        val uri = "https://penguin.upyun.galvincdn.com${zone.background}"
        Glide.with(context)
            .load(uri)
            .placeholder(R.drawable.defaults)
            .into(holder.zoneImage)
        holder.zoneText.text = codeFromI18N(zone.zoneNameI18n, zone.existence)
        holder.zoneCard.setOnClickListener {
            MainScope().launch(Dispatchers.Default) { buildDialog(zone) }
        }
    }

    override fun getItemCount() = zones.size

    private suspend fun buildDialog(zone: ResponseZones) = coroutineScope {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_sec_stage_dialog, null)
        val group: ChipGroup = view.findViewById(R.id.stage_chip_group)
        val stages = zone.stages
        for (s in stages) {
            val chip = Chip(context)
            val find = Repository.readStageById(s)
            val name = codeFromI18N(
                find.codeI18n,
                find.existence
            )
            if (name == "") continue
            chip.run {
                text = name
                isCheckable = true
                isCheckedIconVisible = false
                setOnClickListener {
                    DetailedActivity.start(
                        context, type,
                        find.stageId,
                        "https://penguin.upyun.galvincdn.com${zone.background}"
                    )
                    group.clearCheck()
                }
                group.addView(chip)
            }
        }
        withContext(Dispatchers.Main) {
            MaterialAlertDialogBuilder(context)
                .setCancelable(true)
                .setView(view)
                .setTitle(codeFromI18N(zone.zoneNameI18n, zone.existence))
                .show()
        }
    }
}