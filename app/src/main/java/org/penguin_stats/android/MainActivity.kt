package org.penguin_stats.android

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import org.penguin_stats.android.app.BaseCompatActivity
import org.penguin_stats.android.databinding.ActivityMainBinding

class MainActivity : BaseCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val controller = navHost.navController
        binding.mainBottom.setupWithNavController(controller)
    }

}