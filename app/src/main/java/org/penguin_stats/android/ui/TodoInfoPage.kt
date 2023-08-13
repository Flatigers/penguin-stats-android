package org.penguin_stats.android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.penguin_stats.android.R

class TodoInfoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_info_page)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TodoInfoPage::class.java)
            context.startActivity(intent)
        }
    }
}