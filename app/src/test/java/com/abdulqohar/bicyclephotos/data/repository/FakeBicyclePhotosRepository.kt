package com.abdulqohar.bicyclephotos.data.repository

import com.abdulqohar.bicyclephotos.data.remote.model.Hit
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.domain.repository.BicyclePhotosRepository
import com.abdulqohar.bicyclephotos.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeBicyclePhotosRepository: BicyclePhotosRepository {
    // Flag to control whether the repository should return an empty list
    private var returnEmptyList = false

    // Flag to control whether the repository should return an error
    private var returnError = false

    // Flag to control whether the repository should return a loading state
    private var loading = false

    fun setReturnEmptyList(value: Boolean) {
        returnEmptyList = value
    }

    fun setReturnError(value: Boolean) {
        returnError = value
    }

    fun setLoading(value: Boolean) {
        loading = value
    }

    var testFavouritesDb: MutableList<BicyclePhotoItem> = mutableListOf()

    override suspend fun getBicyclePhotos(): Flow<Resource<MutableList<BicyclePhotoItem>>> {
        // Simulate loading state
        if (loading) {
            return flowOf(Resource.Loading())
        }

        // Simulate returning an error
        if (returnError) {
            return flowOf(Resource.Error("Failed to fetch bicycle photos"))
        }

        // Simulate returning an empty list
        if (returnEmptyList) {
            return flowOf(Resource.Success(mutableListOf()))
        }

        // Simulate returning a list of mock photo items
        val mockPhotoItems = listOf(
            Hit(2, 3, 4, 1, "abcd", 5, "efgh", "", "", "", "", 2, 7, ""),
            Hit(2, 3, 4, 2, "abcd", 5, "efgh", "", "", "", "", 2, 7, "")
        )

        return flowOf(Resource.Success(mockPhotoItems.toMutableList()))
    }

    override suspend fun saveFavoritePhoto(bicyclePhotoItem: BicyclePhotoItem) {
        testFavouritesDb.add(bicyclePhotoItem)
    }

    override suspend fun checkIfPhotoExistsAsFavourite(photoId: Int): Boolean {
        return testFavouritesDb.any { it.id == photoId }
    }

    override suspend fun getAllFavouritePhotos(): Flow<List<BicyclePhotoItem>> {
        return flowOf(testFavouritesDb)
    }
}