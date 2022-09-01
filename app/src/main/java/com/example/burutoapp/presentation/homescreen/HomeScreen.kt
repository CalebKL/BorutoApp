package com.example.burutoapp.presentation.homescreen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.burutoapp.presentation.homescreen.components.HomeTopAppBar

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchClicked = {})
        }
    ){

    }
}