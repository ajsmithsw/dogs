package com.example.dogs.ui.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BreedDetailRoute(
    breed: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: BreedDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    BreedDetailScreen(
        breed,
        sharedTransitionScope,
        animatedContentScope,
        viewModel.uiState.value,
        onBackClick
    )

    LaunchedEffect(Unit) {
        viewModel.handleEvent(BreedUiEvent.GetImagesForBreed(breed))
    }
}