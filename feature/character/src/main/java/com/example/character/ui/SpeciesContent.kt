package com.example.character.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.card.CustomCard
import com.example.character.R
import com.example.domain.model.Specie
import com.example.info.EmptyStateText
import com.example.info.InfoText

@Composable
fun SpeciesContent(
    species: List<Specie>
) {
    if (species.isEmpty()) {
        CustomCard {
            EmptyStateText(
                text = stringResource(R.string.no_species_information_available),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }

    species.forEach { specie ->
        CustomCard {
            InfoText(
                firstLine = stringResource(R.string.name, specie.name),
                secondLine = stringResource(R.string.langue, specie.language),
                firstLineFontWeight = FontWeight.Normal
            )
        }
    }
}