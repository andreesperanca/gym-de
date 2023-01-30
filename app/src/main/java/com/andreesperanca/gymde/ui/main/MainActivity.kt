package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andreesperanca.gymde.R
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView2.id) as NavHostFragment
        navHostFragment.let { navHost ->
            binding.bnvMainBottomNavigation.setupWithNavController(navHost.navController)

            navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
                binding.bnvMainBottomNavigation.visibility =
                    if (destination.id == R.id.workoutDetailsFragment) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }

            }
        }
    }
}