package com.example.burutoapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.LoadState
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.SPACER_HEIGHT

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message = remember{ mutableStateOf(parseErrorMessage(message = error.toString()))}
    val icon = remember { mutableStateOf(R.drawable.ic_network_error)}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = icon,
            contentDescription = stringResource(
                R.string.error_icon)
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT))
        Text(
            text = message,
            fontWeight = FontWeight.Bold,
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
