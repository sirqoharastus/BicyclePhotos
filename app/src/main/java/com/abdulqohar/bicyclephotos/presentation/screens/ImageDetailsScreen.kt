package com.abdulqohar.bicyclephotos.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abdulqohar.bicyclephotos.R
import com.abdulqohar.bicyclephotos.data.local.entity.FavouriteBicyclePhotos
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.presentation.components.TextWithStartIcon
import com.abdulqohar.bicyclephotos.presentation.viewmodel.ImageDetailsViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(bicyclePhotoItem: BicyclePhotoItem?, navController: NavController) {
    val viewModel: ImageDetailsViewModel = hiltViewModel()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bicycle Details",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            GlideImage(
                model = bicyclePhotoItem?.largeImageURL,
                contentDescription = stringResource(R.string.picture_of_bicycle),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 350.dp),
                contentScale = ContentScale.FillWidth,
                loading = placeholder(resourceId = R.drawable.ic_bicycle_vector)
            )

            // Row with three items
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithStartIcon(
                    text = "Add to favourites",
                    icon = Icons.Filled.Favorite,
                    Modifier
                        .weight(1f)
                        .clickable(
                            onClick = {
                                if (bicyclePhotoItem != null) {
                                    viewModel.saveFavouritePhoto(
                                        FavouriteBicyclePhotos(
                                            bicyclePhotoItem.collections,
                                            bicyclePhotoItem.comments,
                                            bicyclePhotoItem.downloads,
                                            bicyclePhotoItem.id,
                                            bicyclePhotoItem.largeImageURL,
                                            bicyclePhotoItem.likes,
                                            bicyclePhotoItem.pageURL,
                                            bicyclePhotoItem.previewURL,
                                            bicyclePhotoItem.type,
                                            bicyclePhotoItem.user,
                                            bicyclePhotoItem.userImageURL,
                                            bicyclePhotoItem.user_id,
                                            bicyclePhotoItem.views,
                                            bicyclePhotoItem.webformatURL
                                        )
                                    )
                                }
                                Toast
                                    .makeText(context, "Saved to Favourites", Toast.LENGTH_LONG)
                                    .show()
                            }
                        )
                )
                TextWithStartIcon(
                    text = bicyclePhotoItem?.downloads?.toString() ?: "",
                    icon = Icons.Filled.AccountCircle,
                    Modifier.weight(1f)
                )
                TextWithStartIcon(
                    text = bicyclePhotoItem?.likes.toString(),
                    icon = Icons.Filled.ThumbUp,
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "\n" +
                        "Bicycles, with their elegant simplicity and timeless design, evoke a sense of freedom, adventure, and sustainability. Their graceful lines and efficient mechanics have captivated hearts for generations, transcending mere transportation to become symbols of style and innovation.\n" +
                        "\n" +
                        "From classic vintage models to sleek modern designs, bicycles embody the perfect harmony between form and function. Whether cruising through bustling city streets or meandering along scenic countryside paths, riding a bicycle offers a unique perspective of the world, allowing one to connect with nature and community in a meaningful way.",
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }
    }


}