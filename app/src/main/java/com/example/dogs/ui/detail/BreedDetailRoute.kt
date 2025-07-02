package com.example.dogs.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BreedDetailRoute(
    breed: String,
    onBackClick: () -> Unit
) {
    val viewModel: BreedDetailViewModel = hiltViewModel()
    BreedDetailScreen(
        breed,
        viewModel.uiState.value,
        onBackClick
    )

    LaunchedEffect(Unit) {
        viewModel.handleEvent(BreedUiEvent.GetImagesForBreed(breed))
    }
}