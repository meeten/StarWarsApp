package com.example.starwarsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


class NavigationState(val navController: NavHostController) {

    fun navigateToCharacter(characterName: String) {
        navController.navigate(
            Screen.Character.createRouteWithArg(characterName)
        )
    }
}

@Composable
fun rememberNavigationState(
    navController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navController)
    }
}
