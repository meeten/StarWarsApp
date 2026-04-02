package com.example.home.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.home.R

@Composable
internal fun CustomSearchBar(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(28.dp),
    onSearch: (String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.tertiary,
                shape = shape
            ),
        value = query,
        onValueChange = {
            query = it
            onSearch(query)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = shape,
        placeholder = {
            Text(
                text = stringResource(R.string.search_characters),
                modifier = Modifier.padding(4.dp)
            )
        }
    )
}