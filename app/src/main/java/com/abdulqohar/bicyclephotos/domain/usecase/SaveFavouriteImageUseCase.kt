package com.abdulqohar.bicyclephotos.domain.usecase

import com.abdulqohar.bicyclephotos.data.local.entity.FavouriteBicyclePhotos
import com.abdulqohar.bicyclephotos.domain.repository.BicyclePhotosRepository
import javax.inject.Inject

class SaveFavouriteImageUseCase @Inject constructor(
    private val bicyclePhotosRepository: BicyclePhotosRepository
) {
    suspend operator fun invoke(favouriteBicyclePhotos: FavouriteBicyclePhotos){
        bicyclePhotosRepository.saveFavoritePhoto(favouriteBicyclePhotos)
    }
}