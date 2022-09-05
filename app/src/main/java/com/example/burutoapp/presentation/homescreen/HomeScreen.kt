package com.example.burutoapp.presentation.homescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.burutoapp.presentation.common.ScreenContent
import com.example.burutoapp.presentation.homescreen.components.HomeTopAppBar
import com.example.burutoapp.presentation.homescreen.components.RatingWidget
import com.example.burutoapp.presentation.theme.EXTRA_LARGE_PADDING

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val homeViewModel= hiltViewModel<HomeViewModel>()
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchClicked = {})
        },
        content = {
            ScreenContent(
                navController = navController,
                heroes = allHeroes
            )
        }
    )
}