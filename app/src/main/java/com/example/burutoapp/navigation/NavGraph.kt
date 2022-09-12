package com.example.burutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.burutoapp.presentation.homescreen.HomeScreen
import com.example.burutoapp.presentation.search.SearchScreen
import com.example.burutoapp.presentation.splash.SplashScreen
import com.example.burutoapp.presentation.splash.SplashViewModel
import com.example.burutoapp.presentation.welcome.WelcomeScreen
import com.example.burutoapp.presentation.welcome.WelcomeViewModel
import com.example.burutoapp.util.Constants.DETAILS_ARGUMENTS_KEY
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENTS_KEY){
                type= NavType.IntType
            })
        ){

        }
        composable(route = Screen.Search.route){
            SearchScreen(navController = navController)
        }
    }
}