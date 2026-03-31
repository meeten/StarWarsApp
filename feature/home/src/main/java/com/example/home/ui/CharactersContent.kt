package com.example.home.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.StarWarsAppTheme
import com.example.domain.model.Character
import com.example.home.extension.getHorizontalStatistics

@Composable
fun CharactersContent(
    characters: List<Character>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = characters, key = { it.name }) { character ->
            CharacterCard(
                name = character.name,
                horizontalStatistics = character.getHorizontalStatistics(),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

private fun getCharactersPreview(): List<Character> {
    val result = mutableListOf<Character>()
    repeat(10) {
        result.add(
            Character(
                name = "Luke Skywalker $it",
                height = "172",
                mass = "77",
                hairColor = "blond",
                eyeColor = "blue",
                birthYear = "",
                gender = "",
                species = emptyList(),
                films = emptyList()
            )
        )
    }
    return result
}

@Preview(
    showBackground = true
)
@Composable
private fun CharactersContentPreview() {
    StarWarsAppTheme {
        CharactersContent(
            characters = getCharactersPreview()
        )
    }
}