package com.abdulqohar.bicyclephotos.domain.usecase

import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.domain.repository.BicyclePhotosRepository
import com.abdulqohar.bicyclephotos.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBicyclePhotosUseCase @Inject constructor(
    private val bicyclePhotosRepository: BicyclePhotosRepository
) {
    suspend operator fun invoke(): Flow<Resource<MutableList<BicyclePhotoItem>>> =
        bicyclePhotosRepository.getBicyclePhotos()
}