package com.example.burutoapp.presentation.homescreen

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.burutoapp.navigation.Screen
import com.example.burutoapp.presentation.common.ScreenContent
import com.example.burutoapp.presentation.homescreen.components.HomeTopAppBar
import com.example.burutoapp.presentation.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val homeViewModel= hiltViewModel<HomeViewModel>()
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchClicked ={
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
            ScreenContent(
                navController = navController,
                heroes = allHeroes
            )
        }
    )
}