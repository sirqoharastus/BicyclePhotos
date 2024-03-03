package com.abdulqohar.bicyclephotos.domain.usecase

import com.abdulqohar.bicyclephotos.domain.repository.BicyclePhotosRepository
import javax.inject.Inject

class CheckIsFavouritePhotoExists @Inject constructor(
    private val bicyclePhotosRepository: BicyclePhotosRepository
) {
    suspend operator fun invoke(photoId: Int): Boolean =
        bicyclePhotosRepository.checkIfPhotoExistsAsFavourite(photoId)
}