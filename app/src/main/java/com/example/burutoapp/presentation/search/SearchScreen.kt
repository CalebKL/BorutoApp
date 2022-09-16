package com.example.burutoapp.presentation.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.burutoapp.presentation.common.ScreenContent
import com.example.burutoapp.presentation.homescreen.HomeViewModel
import com.example.burutoapp.presentation.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )
    val searchViewModel= hiltViewModel<SearchViewModel>()
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChanged = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchedClicked = {
                    searchViewModel.searchedHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ScreenContent(navController = navController, heroes = heroes)
        }
    )
}