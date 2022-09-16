package com.example.burutoapp.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)
val StarColor = Color(0xFFFFC94D)
val shimmerLightGray = Color(0xFFF1F1F1)
val shimmerMediumGray = Color(0xFFE3E3E3)
val shimmerDarkGray = Color(0xFF1D1D1D)
val Colors.welcomeScreenBackground
@Composable
get() = if (isLight) Color.White else DarkGray

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.activeIndicatorColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.inactiveIndicatorColor
    @Composable
    get() = if (isLight) LightGray else DarkGray

val Colors.buttonBackgroundColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.statusBarColor
    @Composable
    get() = if (isLight) Purple500 else Color.Black

val Colors.topAppBarBackgroundColor
    @Composable
    get() = if (isLight) Purple500 else Color.Black

val Colors.topAppBarContentColor
    @Composable
    get() = if (isLight) Color.White else LightGray

val Colors.textColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)