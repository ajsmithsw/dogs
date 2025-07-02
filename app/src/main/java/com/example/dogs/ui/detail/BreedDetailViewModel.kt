package com.example.dogs.ui.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface BreedUiState {
    data class Success(val imageUrls: List<String>) : BreedUiState
    data object Error : BreedUiState
    data object Loading : BreedUiState
}

sealed class BreedUiEvent {
    data class GetImagesForBreed(val breed: String) : BreedUiEvent()
}

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val dogsRepository: DogsRepository
) : ViewModel() {

    private val _uiState: MutableState<BreedUiState> = mutableStateOf(BreedUiState.Loading)
    val uiState: State<BreedUiState> = _uiState

    fun handleEvent(event: BreedUiEvent) {
        when (event) {
            is BreedUiEvent.GetImagesForBreed -> getImagesForBreed(event.breed)
        }
    }

    private fun getImagesForBreed(breed: String) {
        viewModelScope.launch {
            try {
                val imageUrls = dogsRepository.getImagesForBreed(breed)
                val randomImageUrls = imageUrls.shuffled().take(10)
                _uiState.value = BreedUiState.Success(randomImageUrls)
            } catch (e: Exception) {
                _uiState.value = BreedUiState.Error
            }
        }
    }
}