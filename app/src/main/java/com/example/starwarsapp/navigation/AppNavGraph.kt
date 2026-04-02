package com.example.starwarsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    characterScreenContent: @Composable (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            homeScreenContent()
        }

        composable(route = Screen.Character.route) {
            val characterName = it.arguments?.getString(Screen.CHARACTER_NAME_KEY) ?: ""
            characterScreenContent(characterName)
        }
    }
}