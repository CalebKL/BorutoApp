package com.example.burutoapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
       Icon(
           modifier = Modifier.size(ICON_SIZE),
           painter = painterResource(id = icon),
           contentDescription = stringResource(id = R.string.error_icon),
           tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
       )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
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
        message.contains("ConnectionException")->{
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