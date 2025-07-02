package com.example.data.service

import javax.inject.Inject

class DogsServiceImpl @Inject constructor() : DogsService {

    override fun getDogs(): List<String> {
        return listOf("dog1", "dog2", "dog3")
    }
}