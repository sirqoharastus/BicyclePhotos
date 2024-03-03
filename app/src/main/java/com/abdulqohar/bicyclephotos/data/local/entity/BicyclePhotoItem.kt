package com.abdulqohar.bicyclephotos.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import kotlinx.parcelize.Parcelize

@Entity(tableName = "bicycle_photos")
@Parcelize
data class BicyclePhotoItem(
    override val collections: Int,
    override val comments: Int,
    override val downloads: Int,
    @PrimaryKey(autoGenerate = false)
    override val id: Int,
    override val largeImageURL: String,
    override val likes: Int,
    override val pageURL: String,
    override val previewURL: String,
    override val type: String,
    override val user: String,
    override val userImageURL: String,
    override val user_id: Int,
    override val views: Int,
    override val webformatURL: String
) : BicyclePhotoItem, Parcelable {
}