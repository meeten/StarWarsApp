package com.example.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.StarWarsAppTheme

@Composable
fun CharacterCard(
    name: String,
    horizontalStatistics: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = horizontalStatistics,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CharacterCardPreview1() {
    StarWarsAppTheme {
        CharacterCard(
            name = "Luke Skywalker",
            horizontalStatistics = "Height: 172 sm, Mass: 77 kg, Hair: blond, Eyes: blue",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CharacterCardPreview2() {
    StarWarsAppTheme {
        CharacterCard(
            name = "Owen Lars",
            horizontalStatistics = "Height: 178 sm, Mass: 120 kg, Hair: brown, grey, Eyes: blue",
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        )
    }
}