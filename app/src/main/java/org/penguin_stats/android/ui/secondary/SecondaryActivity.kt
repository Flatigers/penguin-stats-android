package org.penguin_stats.android.ui.secondary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import org.penguin_stats.android.R
import org.penguin_stats.android.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    private var titleRes: Int = 0
    private lateinit var binding: ActivitySecondaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.secBar)
        titleRes = intent.getIntExtra("title", 0)
        Log.e("a", titleRes.toString())

        val titleText = resources.getString(titleRes)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = titleText
        }

        val fragment = when (titleRes) {
            R.string.about_dev -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_donate -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_contact -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_content_license -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_members -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_changelog -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_links -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_credit -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_settings -> {
                SettingsPage.new()
            }

            R.string.about_opensource -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            R.string.about_privacy -> {
                SecWebViewerPage.new("https://baidu.com")
            }

            else -> {
                SecWebViewerPage.new("baidu.com")
            }
        }

        supportFragmentManager.commit {
            add(R.id.sec_frame, fragment)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        @JvmStatic
        fun start(context: Context, @StringRes titleRes: Int) {
            val intent = Intent(context, SecondaryActivity::class.java)
            intent.putExtra("title", titleRes)
            context.startActivity(intent)
        }
    }

}