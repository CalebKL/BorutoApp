package com.example.burutoapp.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.burutoapp.navigation.Screen
import com.example.burutoapp.presentation.splash.components.Splash

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val rotate = remember { Animatable(0f) }

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    LaunchedEffect(key1 =true){
        rotate.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted)
            navController.navigate(Screen.Home.route)
        else{
            navController.navigate(Screen.Welcome.route)
        }
    }
    Splash(rotate =rotate.value)
}