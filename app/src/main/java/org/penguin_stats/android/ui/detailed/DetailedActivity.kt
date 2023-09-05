package org.penguin_stats.android.ui.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.penguin_stats.android.R
import org.penguin_stats.android.app.BaseCompatActivity
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.databinding.ActivityDetailedBinding
import org.penguin_stats.android.util.codeFromI18N

class DetailedActivity : BaseCompatActivity() {
    private var pType = QUERY
    private var info = ""
    private var barDrawable = ""
    private var mTitle = "$info : $pType"
    private lateinit var binding: ActivityDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailedRefresher.run {
            setOnRefreshListener {
                MainScope().launch(Dispatchers.IO) {
                    Repository.savePatterns()
                    Repository.saveMatrix()
                    runOnUiThread {
                        isRefreshing = false
                    }
                }
            }
        }

        intent.apply {
            pType = getIntExtra("type", QUERY)
            info = getStringExtra("info")!!
            barDrawable = getStringExtra("drawable")!!
        }

        val fragment = when (pType) {
            QUERY -> DetailStageFragment.new(info)
            REPORT -> DetailReportFragment.new()
            ITEM -> DetailItemFragment.new()
            else -> DetailStageFragment.new(info)
        }
        supportFragmentManager.commit {
            this.replace(R.id.detailed_frame, fragment)
        }

        setSupportActionBar(binding.detailedToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.detailedBarImage
        Glide.with(this)
            .load(barDrawable)
            .placeholder(R.drawable.defaults)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(20, 3)))
            .into(binding.detailedBarImage)

        MainScope().launch(Dispatchers.IO) {
            var subText = ""
            mTitle = when (pType) {
                QUERY -> {
                    val stage = Repository.readStageById(info)
                    val zone = Repository.readZoneById(stage.zoneId)
                    subText = codeFromI18N(zone.zoneNameI18n, zone.existence)
                    codeFromI18N(stage.codeI18n, stage.existence)
                }

                REPORT -> {
                    val stage = Repository.readStageById(info)
                    val zone = Repository.readZoneById(stage.zoneId)
                    subText = codeFromI18N(zone.zoneNameI18n, zone.existence)
                    codeFromI18N(stage.codeI18n, stage.existence)
                }

                ITEM -> {
                    val item = Repository.readItemById(info)
                    codeFromI18N(item.nameI18n, item.existence)
                }

                else -> {
                    "Error matching"
                }
            }

            runOnUiThread {
                supportActionBar?.title = mTitle
                binding.detailedCollapsingBar.title = mTitle
                binding.detailedBarSubtext.text = subText
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        // INFO: stageId
        const val QUERY = 0
        const val REPORT = 1

        // INFO: itemId
        const val ITEM = 2

        @JvmStatic
        fun start(
            context: Context,
            type: Int,
            info: String,
            drawable: String = ""
        ) {
            val intent = Intent(context, DetailedActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("info", info)
            intent.putExtra("drawable", drawable)
            context.startActivity(intent)
        }
    }
}