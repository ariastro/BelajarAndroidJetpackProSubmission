package com.undeadcoder.moviecatalogue.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        setupBottomNavigation()

    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = binding?.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        if (bottomNavigationView != null) {
            NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.navController
            )
        }
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

}