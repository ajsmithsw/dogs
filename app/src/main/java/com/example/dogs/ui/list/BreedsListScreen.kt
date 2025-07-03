@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.dogs.ui.list

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.example.data.model.Breed
import com.example.dogs.R


@OptIn(ExperimentalSharedTransitionApi::class)
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BreedsList(
    breeds: List<Breed>,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBreedClick: (Breed) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            item {
                ListHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                )
            }

            items(breeds) { breed ->
                ListRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    breed = breed,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    onBreedClick = onBreedClick
                )
            }
        }
    }
}

@Composable
private fun ListHeader(
    modifier: Modifier,
) {
    Text(
        text = stringResource(R.string.list_header),
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun ListRow(
    modifier: Modifier,
    breed: Breed,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBreedClick: (Breed) -> Unit
) {
    val locale = Locale.current

    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = breed.name
                    .capitalize(locale),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp)
                    .clickable { onBreedClick(breed) }
                    .padding(8.dp)
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = breed.name),
                        animatedVisibilityScope = animatedContentScope
                    )
            )
        }
    }
}
