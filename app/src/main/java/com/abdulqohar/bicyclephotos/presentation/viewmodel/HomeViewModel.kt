package com.abdulqohar.bicyclephotos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.domain.usecase.GetBicyclePhotosUseCase
import com.abdulqohar.bicyclephotos.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val getBicyclePhotosUseCase: GetBicyclePhotosUseCase): ViewModel() {
    private var _getBicyclePhotosResponse: MutableStateFlow<Resource<MutableList<BicyclePhotoItem>>?> = MutableStateFlow(null)
    val getBicyclePhotosResponse: StateFlow<Resource<MutableList<BicyclePhotoItem>>?> get() = _getBicyclePhotosResponse

    //Calls the use case to fetch the bicycle photos list and update the UI
    fun getBicyclePhotos() {
        viewModelScope.launch {
            _getBicyclePhotosResponse.emit(Resource.Loading())
            getBicyclePhotosUseCase().collect {
                _getBicyclePhotosResponse.emit(it)
            }
        }
    }

}