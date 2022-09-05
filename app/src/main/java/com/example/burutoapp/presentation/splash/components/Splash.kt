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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.Purple500
import com.example.burutoapp.presentation.theme.Purple700

@Composable
fun Splash(rotate: Float) {
    if (isSystemInDarkTheme()){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        )
        {
            Image(
                modifier= Modifier.rotate(degrees = rotate),
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = stringResource(
                    R.string.logo_image)
            )
        }
    }else{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
                .fillMaxSize()
        )
        {
            Image(
                modifier= Modifier.rotate(degrees = rotate),
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = stringResource(
                    R.string.logo_image)
            )
        }
    }
}

@Preview
@Composable
fun SplashPreview() {
    Splash(rotate = 0f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashPreviewDark() {
    Splash(rotate = 0f)
}