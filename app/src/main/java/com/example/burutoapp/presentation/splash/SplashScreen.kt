package com.example.burutoapp.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.burutoapp.presentation.splash.components.Splash
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val rotate = remember { Animatable(0f) }

    LaunchedEffect(key1 =true){
        rotate.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
    }
    Splash(rotate =rotate.value)
}