package com.example.dogs.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Breed
import com.example.data.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface BreedsListUiState {
    data class Success(val breeds: List<Breed>) : BreedsListUiState
    data object Error : BreedsListUiState
    data object Loading : BreedsListUiState
}

sealed interface BreedsListUiEvent {
    data object GetDogBreeds : BreedsListUiEvent
}

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    private val dogsRepository: DogsRepository,
) : ViewModel() {

    private val _uiState: MutableState<BreedsListUiState> = mutableStateOf(BreedsListUiState.Loading)
    val uiState: State<BreedsListUiState> = _uiState

    fun handleEvent(event: BreedsListUiEvent) {
        when (event) {
            BreedsListUiEvent.GetDogBreeds -> getDogBreeds()
        }
    }

    private fun getDogBreeds() {
        viewModelScope.launch {
            try {
                val breeds = dogsRepository.getDogBreeds()
                _uiState.value = BreedsListUiState.Success(breeds)
            } catch (e: Exception) {
                _uiState.value = BreedsListUiState.Error
            }
        }
    }
}