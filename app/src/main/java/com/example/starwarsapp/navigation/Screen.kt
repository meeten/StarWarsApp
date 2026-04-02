package com.example.starwarsapp.navigation

sealed class Screen(val route: String) {

    object Home : Screen(route = HOME_ROUTE)

    object Character : Screen(route = CHARACTER_ROUTE) {
        const val CHARACTER_ROUTE_WITH_ARG = "character"
        fun createRouteWithArg(characterName: String): String {
            return "$CHARACTER_ROUTE_WITH_ARG/$characterName"
        }
    }

    companion object {
        const val CHARACTER_NAME_KEY = "character_name"

        private const val HOME_ROUTE = "home"
        private const val CHARACTER_ROUTE = "character/{$CHARACTER_NAME_KEY}"
    }
}