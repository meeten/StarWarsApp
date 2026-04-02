package com.example.character.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.character.R
import com.example.character.model.FieldInfo
import com.example.domain.model.Character

@Composable
internal fun Character.getFieldsInfo() = listOf(
    FieldInfo(
        title = R.string.birth_year,
        info = birthYear
    ),
    FieldInfo(
        title = R.string.height1,
        info = stringResource(R.string.cm, height)
    ),
    FieldInfo(
        title = R.string.mass1,
        info = stringResource(R.string.kg, mass)
    ),
    FieldInfo(
        title = R.string.gender,
        info = gender
    )
)