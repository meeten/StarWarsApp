package com.example.character.model

import androidx.annotation.StringRes

data class FieldInfo(
    @param:StringRes val title: Int,
    val info: String
)