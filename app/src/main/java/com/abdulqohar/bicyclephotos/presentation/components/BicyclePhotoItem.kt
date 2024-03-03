package com.abdulqohar.bicyclephotos.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.abdulqohar.bicyclephotos.R
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BicyclePhotoItem(bicyclePhotoItem: BicyclePhotoItem) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Card(
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                GlideImage(
                    model = bicyclePhotoItem.largeImageURL,
                    contentDescription = stringResource(R.string.picture_of_bicycle),
                    contentScale = ContentScale.Fit,
                    loading = placeholder(resourceId = R.drawable.ic_bicycle_vector)
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