package com.example.dogs.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dogs.R
import com.example.dogs.ui.theme.DogsTheme

@Composable
fun ErrorScreen(modifier: Modifier, onRetry: () -> Unit) {
    Box(
        modifier = modifier
            .testTag("errorScreen"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.something_went_wrong))
            Button(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
@Preview(apiLevel = 34)
fun PreviewErrorScreen() {
    DogsTheme {
        Surface {
            ErrorScreen(Modifier.fillMaxSize()) {}
        }
    }
}