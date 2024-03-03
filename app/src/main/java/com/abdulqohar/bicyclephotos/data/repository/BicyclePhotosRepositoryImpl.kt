package com.abdulqohar.bicyclephotos.data.repository

import com.abdulqohar.bicyclephotos.data.local.dao.BicycleItemsDao
import com.abdulqohar.bicyclephotos.data.local.entity.FavouriteBicyclePhotos
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.data.remote.BicyclePhotosService
import com.abdulqohar.bicyclephotos.domain.repository.BicyclePhotosRepository
import com.abdulqohar.bicyclephotos.util.ApiCallHandler
import com.abdulqohar.bicyclephotos.util.Resource
import com.abdulqohar.bicyclephotos.util.Routes.Companion.API_KEY
import com.abdulqohar.bicyclephotos.util.Routes.Companion.BICYCLE
import com.abdulqohar.bicyclephotos.util.Routes.Companion.IMAGE_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BicyclePhotosRepositoryImpl @Inject constructor(
    private val bicyclePhotosService: BicyclePhotosService,
    private val bicycleItemsDao: BicycleItemsDao
) : BicyclePhotosRepository {
    override suspend fun getBicyclePhotos(): Flow<Resource<MutableList<BicyclePhotoItem>>> =flow {
        emit(
            ApiCallHandler.safeApiCall(Dispatchers.IO) {
                bicyclePhotosService.getBicyclePhotos(API_KEY, BICYCLE, IMAGE_TYPE ).hits?.toMutableList()?: mutableListOf<BicyclePhotoItem>()
            }
        )
    }

    override suspend fun saveFavoritePhoto(bicyclePhotoItem: BicyclePhotoItem) {
        bicycleItemsDao.saveFavouritePhoto (
            FavouriteBicyclePhotos(
            bicyclePhotoItem.collections, bicyclePhotoItem.comments, bicyclePhotoItem.downloads, bicyclePhotoItem.id,
            bicyclePhotoItem.largeImageURL, bicyclePhotoItem.likes, bicyclePhotoItem.pageURL, bicyclePhotoItem.previewURL,
            bicyclePhotoItem.type, bicyclePhotoItem.user, bicyclePhotoItem.userImageURL, bicyclePhotoItem.user_id,
            bicyclePhotoItem.views, bicyclePhotoItem.webformatURL
        )
        )
    }

    override suspend fun checkIfPhotoExistsAsFavourite(photoId: Int): Boolean {
        return bicycleItemsDao.isItemExists(photoId)
    }

    override suspend fun getAllFavouritePhotos(): Flow<List<BicyclePhotoItem>> {
        return bicycleItemsDao.getAllItems()
    }

}