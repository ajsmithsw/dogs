package com.example.dogs

import androidx.lifecycle.ViewModel
import com.example.data.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dogsRepository: DogsRepository
) : ViewModel() {

    val testValue = dogsRepository.getDogs().joinToString(", ")
}