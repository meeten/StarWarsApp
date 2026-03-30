package com.example.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorSchemeApp = lightColorScheme(
    primary = Color.White,
    secondary = lightLavenderGray,
    tertiary = Color.Gray,

    onPrimary = Color.Black,
    onSecondary = lightBlue
)

@Composable
fun StarWarsAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorSchemeApp,
        typography = Typography,
        content = content
    )
}