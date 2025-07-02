package com.example.dogs.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.model.Breed


@Composable
fun BreedsListRoute(
    viewModel: BreedsListViewModel = hiltViewModel(),
    onBreedClick: (Breed) -> Unit
) {
    BreedsListScreen(viewModel.uiState.value, onBreedClick)

    LaunchedEffect(Unit) {
        viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds)
    }
}
