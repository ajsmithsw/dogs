@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.dogs.ui.list

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.model.Breed


@Composable
fun BreedsListScreen(
    uiState: BreedsListUiState,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBreedClick: (Breed) -> Unit
) {
    when (uiState) {
        BreedsListUiState.Error -> Text("Error")
        BreedsListUiState.Loading -> Text("Loading")
        is BreedsListUiState.Success -> BreedsList(
            uiState.breeds,
            sharedTransitionScope,
            animatedContentScope,
            onBreedClick
        )
    }
}

@Composable
fun BreedsList(
    breeds: List<Breed>,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBreedClick: (Breed) -> Unit
) {
    with(sharedTransitionScope) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LazyColumn(
                Modifier.padding(innerPadding)
            ) {
                items(breeds) { breed ->
                    Text(
                        text = breed.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onBreedClick(breed) }
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = breed.name),
                                animatedVisibilityScope = animatedContentScope
                            )
                    )
                }
            }
        }
    }
}