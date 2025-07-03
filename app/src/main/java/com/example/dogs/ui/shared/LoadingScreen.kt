package com.example.dogs.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.dogs.ui.theme.DogsTheme

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(modifier
        .testTag("loadingScreen"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
fun PreviewLoadingScreen() {
    DogsTheme {
        Surface {
            LoadingScreen(Modifier.fillMaxSize())
        }
    }
}