package com.example.burutoapp.presentation.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.TOP_APP_BAR_HEIGHT
import com.example.burutoapp.presentation.theme.topAppBarBackgroundColor
import com.example.burutoapp.presentation.theme.topAppBarContentColor

@Composable
fun SearchTopBar(
    text: String,
    onTextChanged:(String)-> Unit,
    onSearchedClicked:(String)->Unit,
    onCloseClicked:(String)->Unit
){

}

@Composable
fun SearchWidget(
    text: String,
    onTextChanged:(String)-> Unit,
    onSearchedClicked:(String)->Unit,
    onCloseClicked:(String)->Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ){
        TextField(
            value = text,
            onValueChange ={
                onTextChanged(it)
        }, 
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(R.string.search_hero),
                    color = Color.White
                )
            }
        )
    }
}