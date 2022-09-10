package com.example.burutoapp.presentation.common

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.ICON_SIZE
import com.example.burutoapp.presentation.theme.SMALL_PADDING
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember{ mutableStateOf(parseErrorMessage(message = error.toString()))}
    val icon by remember { mutableStateOf(R.drawable.ic_network_error)}

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
       Icon(
           modifier = Modifier
               .size(ICON_SIZE)
               .alpha(alpha = alphaAnim),
           painter = painterResource(id = icon),
           contentDescription = stringResource(id = R.string.error_icon),
           tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
       )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
            modifier = Modifier.alpha(alpha = alphaAnim),
            text = message,
            color= if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            textAlign= TextAlign.Center,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

fun parseErrorMessage(message:String): String{
    return when{
        message.contains("SocketTimeoutException")->{
            "Server Unavailable"
        }
        message.contains("ConnectException")->{
            "Internet Unavailable"
        }else ->{
            "Unknown Error"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPreview() {
    EmptyScreen(error = LoadState.Error(error = SocketTimeoutException()))
}