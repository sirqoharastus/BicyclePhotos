package com.abdulqohar.bicyclephotos.di

import android.content.Context
import androidx.room.Room
import com.abdulqohar.bicyclephotos.data.local.dao.BicycleItemsDao
import com.abdulqohar.bicyclephotos.data.local.database.BicyclePhotosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesBicyclePhotosDatabase(@ApplicationContext context: Context): BicyclePhotosDatabase =
        Room.databaseBuilder(
            context,
            BicyclePhotosDatabase::class.java,
            BicyclePhotosDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesBicyclePhotosDao(bicyclePhotosDatabase: BicyclePhotosDatabase): BicycleItemsDao =
        bicyclePhotosDatabase.getBicyclePhotosDao()
}