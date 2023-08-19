package org.penguin_stats.android.ui.secondary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.penguin_stats.android.R
import org.penguin_stats.android.data.DefaultI18N
import org.penguin_stats.android.data.StageUI
import org.penguin_stats.android.data.ZoneUI
import org.penguin_stats.android.ui.detailed.DetailedActivity
import org.penguin_stats.android.util.codeFromI18N

class StageZonesAdapter(
    val context: Context, val type: Int,
    private val zones: List<ZoneUI>, private val stageList: List<StageUI>
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
        holder.zoneImage.setImageResource(R.drawable.icon_game_42)
        holder.zoneText.text = codeFromI18N(zone.zoneNameI18n, zone.existence)
        holder.zoneCard.setOnClickListener {
            buildDialog(zone)
        }
    }

    override fun getItemCount() = zones.size

    private fun buildDialog(zone: ZoneUI) {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_sec_stage_dialog, null)
        val group: ChipGroup = view.findViewById(R.id.stage_chip_group)
        val stages = zone.stages
        for (s in stages) {
            val chip = Chip(context)
            val find = stageList.find { it.stageId == s }
            val name = codeFromI18N(
                find?.code_i18n ?: DefaultI18N.defaultCodeI18N(),
                find?.existence ?: DefaultI18N.defaultExistence()
            )
            if (name == "") {
                continue
            }
            chip.run {
                text = name
                isCheckable = true
                isCheckedIconVisible = false
                setOnClickListener {
                    DetailedActivity.start(context, type, find?.stageId ?: "null find")
                    group.clearCheck()
                }
                group.addView(chip)
            }
        }

        MaterialAlertDialogBuilder(context)
            .setCancelable(true)
            .setNegativeButton("cancel") { _, _ -> }
            .setView(view)
            .setTitle(codeFromI18N(zone.zoneNameI18n, zone.existence))
            .show()
    }
}