package com.abdulqohar.bicyclephotos.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdulqohar.bicyclephotos.data.local.dao.BicycleItemsDao
import com.abdulqohar.bicyclephotos.data.local.entity.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.data.local.entity.FavouriteBicyclePhotos

@Database(entities = [BicyclePhotoItem::class, FavouriteBicyclePhotos::class], version = 1)
abstract class BicyclePhotosDatabase: RoomDatabase() {

    abstract fun getBicyclePhotosDao(): BicycleItemsDao

    companion object {
        val DATABASE_NAME = "BicyclePhotosDatabase"
    }
}