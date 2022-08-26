package com.example.burutoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.burutoapp.navigation.SetupNavigation
import com.example.burutoapp.presentation.theme.BurutoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BurutoAppTheme {
                navController = rememberNavController()
                SetupNavigation(navController = navController)
            }
        }
    }
}

