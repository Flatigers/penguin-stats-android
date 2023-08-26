package org.penguin_stats.android.ui.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
        when (pType) {
            QUERY -> {
                viewQuery()
            }

            REPORT -> {
                viewReport()
            }

            ITEM -> {
                viewItem()
            }

            RECO -> {
                viewReco()
            }
        }

        setSupportActionBar(binding.detailedToolBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "$info : $pType"
        }
        binding.detailedCollapsingBar.title = "$info : $pType"
        binding.detailedBarImage
        Glide.with(this)
            .load(barDrawable)
            .placeholder(R.drawable.defaults)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.detailedBarImage)
    }

    private fun viewQuery() {

    }

    private fun viewReport() {

    }

    private fun viewItem() {

    }

    private fun viewReco() {

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
        const val RECO = 4

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