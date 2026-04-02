package com.example.starwarsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.character.CharacterScreen
import com.example.designsystem.theme.StarWarsAppTheme
import com.example.home.HomeScreen
import com.example.starwarsapp.navigation.AppNavGraph
import com.example.starwarsapp.navigation.rememberNavigationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationState = rememberNavigationState()

            StarWarsAppTheme {
                Column(modifier = Modifier.padding(16.dp)) {
                    AppNavGraph(
                        navController = navigationState.navController,
                        homeScreenContent = {
                            HomeScreen { characterName ->
                                navigationState.navigateToCharacter(characterName)
                            }
                        },
                        characterScreenContent = { characterName ->
                            CharacterScreen(
                                name = characterName
                            ) {
                                navigationState.navController.popBackStack()
                            }
                        }
                    )
                }
            }
        }
    }
}