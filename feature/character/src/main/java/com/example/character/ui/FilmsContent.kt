package com.example.character.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.card.CustomCard
import com.example.character.R
import com.example.domain.model.Film
import com.example.info.InfoText

@Composable
fun FilmsContent(
    films: List<Film>,
    modifier: Modifier = Modifier
) {
    if (films.isEmpty()) {
        CustomCard {
            Text(
                text = stringResource(R.string.no_species_information_available),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
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