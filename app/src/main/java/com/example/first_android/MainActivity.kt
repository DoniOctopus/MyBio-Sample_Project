package com.example.first_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = (nav_main_host_fragment_container as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottom_navigation, navController)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.showFood -> {
                    navController.navigate(R.id.action_to_fragment_film)
                    true
                }

                R.id.createFood -> {
                    navController.navigate(R.id.action_to_fragment_create_film)
                    true
                }
                else -> {
                    println("MASUK ELSE")
                    false
                }
            }
        }

    }


}


