package com.example.character.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.card.CustomCard
import com.example.character.R
import com.example.domain.model.Film
import com.example.info.EmptyStateText
import com.example.info.InfoText

@Composable
fun FilmsContent(
    films: List<Film>,
    modifier: Modifier = Modifier
) {
    if (films.isEmpty()) {
        CustomCard {
            EmptyStateText(
                text = stringResource(R.string.no_films_information_available),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }

    Column(modifier = modifier) {
        films.forEach { film ->
            CustomCard {
                InfoText(
                    firstLine = film.title,
                    secondLine = film.openingCrawl,
                    secondLineMaxLines = 3
                )
            }
        }
    }
}