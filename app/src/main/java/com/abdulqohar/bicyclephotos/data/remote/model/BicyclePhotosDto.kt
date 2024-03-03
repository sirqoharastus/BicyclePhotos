package com.abdulqohar.bicyclephotos.data.remote.model

data class BicyclePhotosDto(
    val hits: List<Hit>?,
    val total: Int?= 0,
    val totalHits: Int? = 0
)