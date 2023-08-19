package org.penguin_stats.android.ui.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.penguin_stats.android.R

class DetailedActivity : AppCompatActivity() {
    private var pType = QUERY
    private var info = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        intent.apply {
            pType = getIntExtra("type", QUERY)
            info = getStringExtra("info")!!
        }
        Toast.makeText(this, "pType: $pType \ninfo: $info", Toast.LENGTH_SHORT).show()

    }

    companion object {
        const val QUERY = 0
        const val REPORT = 1
        const val ITEM = 2
        const val RECO = 4

        @JvmStatic
        fun start(context: Context, type: Int, info: String) {
            val intent = Intent(context, DetailedActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("info", info)
            context.startActivity(intent)
        }
    }
}