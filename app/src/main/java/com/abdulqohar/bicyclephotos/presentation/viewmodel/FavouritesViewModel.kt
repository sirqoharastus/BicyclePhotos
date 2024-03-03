package com.abdulqohar.bicyclephotos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.domain.usecase.GetAllFavouritePhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getAllFavouritePhotosUseCase: GetAllFavouritePhotosUseCase
): ViewModel() {
    var _favouritePhotosList: MutableStateFlow<List<BicyclePhotoItem>?> = MutableStateFlow(null)
        private set

    fun getAllFavouritePhotos() {
        viewModelScope.launch {
            getAllFavouritePhotosUseCase().collect {
                _favouritePhotosList.emit(it)
            }
        }
    }
}