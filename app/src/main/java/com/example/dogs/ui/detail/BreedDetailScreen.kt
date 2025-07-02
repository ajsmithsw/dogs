package com.example.dogs.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailScreen(
    uiState: BreedUiState,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(uiState.breedName ?: "")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                })
        }
    ) { innerPadding ->
        when (uiState) {
            BreedUiState.Error -> Text("Error")
            BreedUiState.Loading -> Text("Loading")
            is BreedUiState.Success -> BreedDetail(uiState.breedName)
        }
    }
}

@Composable
fun BreedDetail(
    breedName: String
) {
    Text(text = breedName)
}