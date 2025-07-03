package com.example.dogs.ui.list

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.model.Breed


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BreedsListRoute(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: BreedsListViewModel = hiltViewModel(),
    onBreedClick: (Breed) -> Unit
) {
    BreedsListScreen(
        viewModel.uiState.value,
        sharedTransitionScope,
        animatedContentScope,
        onBreedClick,
        onRetryClick = { viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds) }
    )

    LaunchedEffect(Unit) {
        viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds)
    }
}
