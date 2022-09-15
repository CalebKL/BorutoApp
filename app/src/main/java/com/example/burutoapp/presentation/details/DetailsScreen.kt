package com.example.burutoapp.presentation.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.burutoapp.presentation.details.components.DetailsContent

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController:NavHostController
) {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val selectedHero by detailsViewModel.selectedHero
    DetailsContent(navController = navController, selectedHero = selectedHero)
}