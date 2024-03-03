package com.abdulqohar.bicyclephotos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulqohar.bicyclephotos.data.local.entity.FavouriteBicyclePhotos
import com.abdulqohar.bicyclephotos.domain.usecase.CheckIsFavouritePhotoExists
import com.abdulqohar.bicyclephotos.domain.usecase.SaveFavouriteImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    private val checkIsFavouritePhotoExists: CheckIsFavouritePhotoExists,
    private val saveFavouriteImageUseCase: SaveFavouriteImageUseCase
): ViewModel() {


    fun saveFavouritePhoto(favouriteBicyclePhotos: FavouriteBicyclePhotos) {
        viewModelScope.launch {
            saveFavouriteImageUseCase(favouriteBicyclePhotos)
        }
    }

    fun checkIfFavouritePhotoExists(photoId: Int, favouritePhotoExists:(exists: Boolean) -> Unit) {
        viewModelScope.launch {
            favouritePhotoExists(checkIsFavouritePhotoExists(photoId))
        }
    }

}