package com.example.data.repository

import com.example.data.service.DogsService
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val dogsService: DogsService
) : DogsRepository {

    override fun getDogs(): List<String> {
        return dogsService.getDogs()
    }
}