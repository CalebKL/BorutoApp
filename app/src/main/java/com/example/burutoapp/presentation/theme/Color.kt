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

val Colors.welcomeScreenBackground
@Composable
get() = if (isLight) LightGray else DarkGray

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.textColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)