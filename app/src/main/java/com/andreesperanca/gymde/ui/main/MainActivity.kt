package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andreesperanca.gymde.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        synchronizeNavigation()
    }

    private fun synchronizeNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView2.id) as NavHostFragment
        navHostFragment.let { navHost ->
            binding.bnvMainBottomNavigation.setupWithNavController(navHost.navController)
        }
    }
}