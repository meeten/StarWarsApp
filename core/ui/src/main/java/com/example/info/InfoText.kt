package com.example.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun InfoText(
    firstLine: String,
    secondLine: String,
    modifier: Modifier = Modifier,
    firstLineColor: Color = MaterialTheme.colorScheme.onPrimary,
    firstLineFontWeight: FontWeight = FontWeight.Bold,
    secondLineMaxLines: Int = 1,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = firstLine,
            color = firstLineColor,
            fontWeight = firstLineFontWeight
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = secondLine,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                overflow = TextOverflow.Ellipsis,
                maxLines = secondLineMaxLines,
            )
        }
    }
}