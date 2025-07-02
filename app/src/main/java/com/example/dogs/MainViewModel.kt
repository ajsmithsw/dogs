package com.example.dogs

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

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dogsRepository: DogsRepository
) : ViewModel() {

    private val _uiState: MutableState<List<Breed>> = mutableStateOf(emptyList())
    val uiState: State<List<Breed>> = _uiState

    init {
        viewModelScope.launch {
            val breeds = dogsRepository.getDogBreeds()
            _uiState.value = breeds
        }
    }
}