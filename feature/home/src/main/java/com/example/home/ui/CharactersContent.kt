package com.example.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.card.CustomCard
import com.example.designsystem.theme.StarWarsAppTheme
import com.example.domain.model.Character
import com.example.home.extension.getHorizontalStatistics
import com.example.info.InfoText
import com.example.loading.Loading

@Composable
fun CharactersContent(
    characters: List<Character>,
    isLoadingNext: Boolean,
    modifier: Modifier = Modifier,
    loadNextData: () -> Unit,
    onClickCharacter: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = characters, key = { it.name }) { character ->
            CustomCard(
                modifier = Modifier
                    .clickable {
                        onClickCharacter(character.name)
                    }
            ) {
                InfoText(
                    firstLine = character.name,
                    secondLine = character.getHorizontalStatistics(),
                    firstLineColor = MaterialTheme.colorScheme.onSecondary
                )
            }
        }

        item {
            if (isLoadingNext) {
                Loading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            } else {
                SideEffect {
                    loadNextData()
                }
            }
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
            characters = getCharactersPreview(),
            isLoadingNext = false,
            loadNextData = {},
            onClickCharacter = {}
        )
    }
}