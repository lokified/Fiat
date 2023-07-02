package com.loki.fiat.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    message: String = "",
    color: Color = MaterialTheme.colorScheme.error
) {

    val col = if (message == "No search result") MaterialTheme.colorScheme.onBackground else color

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (message.isNotBlank()) {
            Text(text = message, color = col)
        }
    }
}