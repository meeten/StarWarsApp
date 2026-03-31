package com.example.home.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.domain.model.Character
import com.example.home.R

@Composable
internal fun Character.getHorizontalStatistics(): String =
    listOf(
        stringResource(R.string.height, height),
        stringResource(R.string.mass, mass),
        stringResource(R.string.hair, hairColor),
        stringResource(R.string.eyes, eyeColor)
    ).joinToString(", ")