package com.example.topbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.ui.R

@Composable
fun NavigationBackIcon(
    onClickBack: () -> Unit,
    modifier: Modifier
) {
    IconButton(
        modifier = Modifier,
        onClick = onClickBack
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow_back_24px),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}