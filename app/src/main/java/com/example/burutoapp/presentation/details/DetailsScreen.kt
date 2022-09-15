package com.example.burutoapp.presentation.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.burutoapp.presentation.details.components.DetailsContent

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController:NavHostController
) {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    DetailsContent(navController = navController, selectedHero = selectedHero)
}