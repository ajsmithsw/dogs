package com.example.data.model

import com.google.gson.annotations.SerializedName

data class DogImagesResponse(
    @SerializedName("message") val imageUrls: List<String>,
    @SerializedName("status") val status: String
)