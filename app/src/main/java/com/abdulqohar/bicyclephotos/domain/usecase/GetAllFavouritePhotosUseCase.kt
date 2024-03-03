package com.abdulqohar.bicyclephotos.domain.usecase

import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.domain.repository.BicyclePhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavouritePhotosUseCase @Inject constructor(
    private val bicyclePhotosRepository: BicyclePhotosRepository
) {
    suspend operator fun invoke(): Flow<List<BicyclePhotoItem>> =
        bicyclePhotosRepository.getAllFavouritePhotos()
}