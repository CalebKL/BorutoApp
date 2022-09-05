package com.example.burutoapp.presentation.homescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.burutoapp.presentation.homescreen.components.HomeTopAppBar
import com.example.burutoapp.presentation.homescreen.components.RatingWidget
import com.example.burutoapp.presentation.theme.EXTRA_LARGE_PADDING

@Composable
fun HomeScreen() {
    val homeViewModel= hiltViewModel<HomeViewModel>()
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchClicked = {})
        }
    ){
        RatingWidget(modifier = Modifier.padding(EXTRA_LARGE_PADDING), rating = 4.1)
    }
}