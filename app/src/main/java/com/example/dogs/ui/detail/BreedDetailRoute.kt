package com.example.dogs.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BreedDetailRoute(
    breed: String,
    viewModel: BreedDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    BreedDetailScreen(
        breed,
        viewModel.uiState.value,
        onBackClick
    )

    LaunchedEffect(Unit) {
        viewModel.handleEvent(BreedUiEvent.GetImagesForBreed(breed))
    }
}