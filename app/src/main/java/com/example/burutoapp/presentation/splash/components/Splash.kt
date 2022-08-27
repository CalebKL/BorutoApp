package com.example.burutoapp.presentation.splash.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.burutoapp.R
import com.example.burutoapp.navigation.Screen
import com.example.burutoapp.presentation.splash.SplashScreen
import com.example.burutoapp.presentation.theme.Purple500
import com.example.burutoapp.presentation.theme.Purple700

@Composable
fun Splash() {
    if (isSystemInDarkTheme()){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        )
        {
            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = stringResource(
                R.string.logo_image))
        }
    }else{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
                .fillMaxSize()
        )
        {
            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = stringResource(
                R.string.logo_image))
        }
    }
}

@Preview
@Composable
fun SplashPreview() {
    Splash()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashPreviewDark() {
    Splash()
}