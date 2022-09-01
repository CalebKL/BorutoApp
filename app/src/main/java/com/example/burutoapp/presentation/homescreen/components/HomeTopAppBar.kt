package com.example.burutoapp.presentation.homescreen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.topAppBarBackgroundColor
import com.example.burutoapp.presentation.theme.topAppBarContentColor

@Composable
fun HomeTopAppBar(
    onSearchClicked: () ->Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        }
    )
}

@Preview
@Composable
fun PrevHomeTopAppBar() {
    HomeTopAppBar(onSearchClicked = {})
}