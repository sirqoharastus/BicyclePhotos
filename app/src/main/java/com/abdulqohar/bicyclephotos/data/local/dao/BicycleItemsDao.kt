package com.abdulqohar.bicyclephotos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdulqohar.bicyclephotos.data.local.entity.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.data.local.entity.FavouriteBicyclePhotos
import kotlinx.coroutines.flow.Flow

@Dao
interface BicycleItemsDao {


    @Query("SELECT * FROM bicycle_photos")
    fun getAllBicyclePhotos(): Flow<MutableList<BicyclePhotoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBicycle(bicyclePhotoItem: BicyclePhotoItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBicycles(bicyclePhotoItems: MutableList<BicyclePhotoItem>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavouritePhoto(item: FavouriteBicyclePhotos)

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_bicycle_photos WHERE id = :id)")
    suspend fun isItemExists(id: Int): Boolean

    @Query("SELECT * FROM favourite_bicycle_photos")
    fun getAllItems(): Flow<List<FavouriteBicyclePhotos>>
}