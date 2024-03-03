package com.abdulqohar.bicyclephotos.domain.model

import android.os.Parcelable

interface BicyclePhotoItem: Parcelable {
    val collections: Int
    val comments: Int
    val downloads: Int
    val id: Int
    val largeImageURL: String
    val likes: Int
    val pageURL: String
    val previewURL: String
    val type: String
    val user: String
    val userImageURL: String
    val user_id: Int
    val views: Int
    val webformatURL: String
}