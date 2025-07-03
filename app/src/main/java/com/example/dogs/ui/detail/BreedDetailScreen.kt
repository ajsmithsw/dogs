@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.dogs.ui.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dogs.ui.shared.ErrorScreen
import com.example.dogs.ui.shared.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailScreen(
    breed: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    uiState: BreedUiState,
    onBackClick: () -> Unit,
    onRetryClick: (String) -> Unit
) {
    val locale = Locale.current

    with(sharedTransitionScope) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            breed.capitalize(locale),
                            modifier = Modifier.sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = breed),
                                animatedVisibilityScope = animatedContentScope
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    })
            }
        ) { innerPadding ->
            when (uiState) {
                BreedUiState.Error -> ErrorScreen(
                    modifier = Modifier.padding(innerPadding),
                    onRetry = { onRetryClick(breed) }
                )
                BreedUiState.Loading -> LoadingScreen(
                    modifier = Modifier.padding(innerPadding)
                )
                is BreedUiState.Success -> BreedDetail(
                    modifier = Modifier.padding(innerPadding),
                    imageUrls = uiState.imageUrls
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedDetail(
    modifier: Modifier,
    imageUrls: List<String>
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(imageUrls) {
            GlideImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}