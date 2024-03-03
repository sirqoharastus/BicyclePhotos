package com.abdulqohar.bicyclephotos.domain.repository

import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.util.Resource
import kotlinx.coroutines.flow.Flow

interface BicyclePhotosRepository {

    suspend fun getBicyclePhotos(): Flow<Resource<MutableList<BicyclePhotoItem>>>

    suspend fun saveFavoritePhoto(bicyclePhotoItem: BicyclePhotoItem)

    suspend fun checkIfPhotoExistsAsFavourite(photoId: Int): Boolean

    suspend fun getAllFavouritePhotos(): Flow<List<BicyclePhotoItem>>
}