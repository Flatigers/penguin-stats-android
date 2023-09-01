package org.penguin_stats.android.ui.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import org.penguin_stats.android.R
import org.penguin_stats.android.app.BaseCompatActivity
import org.penguin_stats.android.databinding.ActivityDetailedBinding

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

        intent.apply {
            pType = getIntExtra("type", QUERY)
            info = getStringExtra("info")!!
            barDrawable = getStringExtra("drawable")!!
        }

        val fragment = when (pType) {
            QUERY -> DetailStageFragment.new()
            REPORT -> DetailReportFragment.new()
            ITEM -> DetailItemFragment.new()
            else -> DetailStageFragment.new()
        }
        supportFragmentManager.commit {
            this.replace(R.id.detailed_frame, fragment)
        }

        setSupportActionBar(binding.detailedToolBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = mTitle
        }
        binding.detailedCollapsingBar.title = mTitle
        binding.detailedBarImage
        Glide.with(this)
            .load(barDrawable)
            .placeholder(R.drawable.defaults)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(15, 3)))
            .into(binding.detailedBarImage)
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
        const val QUERY = 0
        const val REPORT = 1
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