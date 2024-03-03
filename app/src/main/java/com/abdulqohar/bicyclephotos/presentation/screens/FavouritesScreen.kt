package com.abdulqohar.bicyclephotos.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.presentation.viewmodel.FavouritesViewModel

@Composable
fun FavouritesScreen(viewModel: FavouritesViewModel = hiltViewModel()) {
    viewModel.getAllFavouritePhotos()
    val favouritePhotosList by viewModel._favouritePhotosList.collectAsState()
        FavouritePhotosList(favouritePhotosList)

}



@Composable
fun FavouritePhotosList(favouritePhotos: List<BicyclePhotoItem>?) {
    LazyColumn {
        items(favouritePhotos ?: emptyList()) { photoItem ->
            BicyclePhotoItem(bicyclePhotoItem = photoItem, navigateToDetails = {

            })
        }
    }
}