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

sealed interface MainUiState {
    data class Success(val breeds: List<Breed>) : MainUiState
    data object Error : MainUiState
    data object Loading : MainUiState
}

sealed interface MainUiEvent {
    data object GetDogBreeds : MainUiEvent
}

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    private val dogsRepository: DogsRepository
) : ViewModel() {

    private val _uiState: MutableState<MainUiState> = mutableStateOf(MainUiState.Loading)
    val uiState: State<MainUiState> = _uiState

    fun handleEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.GetDogBreeds -> getDogBreeds()
        }
    }

    private fun getDogBreeds() {
        viewModelScope.launch {
            try {
                val breeds = dogsRepository.getDogBreeds()
                _uiState.value = MainUiState.Success(breeds)
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error
            }
        }
    }
}