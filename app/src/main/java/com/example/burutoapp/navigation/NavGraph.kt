package com.example.burutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.burutoapp.presentation.splash.SplashScreen
import com.example.burutoapp.util.Constants.DETAILS_ARGUMENTS_KEY

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route){

        }
        composable(route = Screen.Home.route){

        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENTS_KEY){
                type= NavType.IntType
            })
        ){

        }
        composable(route = Screen.Search.route){

        }
    }
}