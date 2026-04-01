package com.example.character.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.card.CustomCard
import com.example.character.R
import com.example.character.model.FieldInfo
import com.example.domain.model.Film
import com.example.domain.model.Specie
import com.example.info.InfoText

@Composable
fun CharacterContent(
    fieldsInfo: List<FieldInfo>,
    species: List<Specie>,
    films: List<Film>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)) {
        TitleText(text = stringResource(R.string.bacis_information))
        fieldsInfo.forEach { fieldInfo ->
            CustomCard {
                InfoText(
                    firstLine = stringResource(fieldInfo.title),
                    secondLine = fieldInfo.info,
                    firstLineFontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TitleText(text = stringResource(R.string.species))
        SpeciesContent(species)

        Spacer(modifier = Modifier.height(16.dp))

        TitleText(text = stringResource(R.string.films))
        FilmsContent(films)
    }
}