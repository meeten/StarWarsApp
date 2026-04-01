package com.example.character.extension

import com.example.character.R
import com.example.character.model.FieldInfo
import com.example.domain.model.Character

internal fun Character.getFieldsInfo() = listOf(
    FieldInfo(
        title = R.string.birth_year,
        info = birthYear
    ),
    FieldInfo(
        title = R.string.height,
        info = height
    ),
    FieldInfo(
        title = R.string.mass,
        info = mass
    ),
    FieldInfo(
        title = R.string.gender,
        info = gender
    )
)