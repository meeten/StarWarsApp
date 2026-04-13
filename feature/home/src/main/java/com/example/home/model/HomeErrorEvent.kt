package com.example.home.model

import androidx.annotation.StringRes

sealed interface HomeErrorEvent {

    data class ShowError(@param:StringRes val message: Int) : HomeErrorEvent
}
