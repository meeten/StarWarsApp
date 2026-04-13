package com.example.extension

import com.example.ui.R
import java.net.UnknownHostException

fun Throwable.toUserFriendlyMessage(): Int {
    return when (this) {
        is UnknownHostException -> R.string.no_internet_connection
        else -> R.string.unknown_error
    }
}