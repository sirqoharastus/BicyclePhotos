package com.abdulqohar.bicyclephotos.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdulqohar.bicyclephotos.R
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.presentation.components.TextWithStartIcon
import com.abdulqohar.bicyclephotos.presentation.viewmodel.HomeViewModel
import com.abdulqohar.bicyclephotos.util.Resource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToDetails: (bicycleItem: BicyclePhotoItem) -> Unit) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    homeViewModel.getBicyclePhotos()
    val bicyclePhotosState by homeViewModel.getBicyclePhotosResponse.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bicycle Photos",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppins_bold,
                                weight = FontWeight.Normal,
                                style = FontStyle.Normal
                            )
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(20.dp))
            when (val resource = bicyclePhotosState) {
                is Resource.Loading -> {
                    // Display loading indicator
                    CircularProgressIndicator(color = Color.Green, trackColor = Color.Red)
                }
                is Resource.Success -> {
                    // Display the list
                    BicyclePhotosList(resource.data, navigateToDetails)
                }
                is Resource.Error -> {
                    // Display error message
                    Text(
                        text = "Error: ${resource.message}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                else -> {}
            }
        }
    }
}

@Composable
fun BicyclePhotosList(bicyclePhotos: List<BicyclePhotoItem>, navigateToDetails: (bicycleItem: BicyclePhotoItem) -> Unit) {
    LazyColumn {
        items(items = bicyclePhotos) { photoItem ->
            // Displays individual photo item
            BicyclePhotoItem(bicyclePhotoItem = photoItem, navigateToDetails)

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BicyclePhotoItem(bicyclePhotoItem: BicyclePhotoItem, navigateToDetails: (bicycleItem: BicyclePhotoItem)-> Unit) {
    Column() {
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            onClick = {
                navigateToDetails(bicyclePhotoItem)
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                GlideImage(
                    model = bicyclePhotoItem.largeImageURL,
                    contentDescription = stringResource(R.string.picture_of_bicycle),
                    contentScale = ContentScale.FillWidth,
                    loading = placeholder(resourceId = R.drawable.ic_bicycle_vector),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = bicyclePhotoItem.user,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppins_medium,
                                weight = FontWeight.Normal,
                                style = FontStyle.Normal
                            )
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp, 5.dp)
                            .weight(1f),
                        maxLines = 1,
                    )
                    TextWithStartIcon(
                        text = bicyclePhotoItem.downloads.toString(),
                        icon = Icons.Filled.AccountCircle,
                        Modifier.weight(1f)
                    )
                    TextWithStartIcon(
                        text = bicyclePhotoItem.likes.toString(),
                        icon = Icons.Filled.ThumbUp,
                        Modifier.weight(1f)
                    )
                }

            }
        }
    }
}