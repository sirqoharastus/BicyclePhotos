package com.abdulqohar.bicyclephotos.di

import com.abdulqohar.bicyclephotos.data.local.dao.BicycleItemsDao
import com.abdulqohar.bicyclephotos.data.repository.BicyclePhotosRepositoryImpl
import com.abdulqohar.bicyclephotos.data.remote.BicyclePhotosService
import com.abdulqohar.bicyclephotos.domain.repository.BicyclePhotosRepository
import com.abdulqohar.bicyclephotos.domain.usecase.CheckIsFavouritePhotoExists
import com.abdulqohar.bicyclephotos.domain.usecase.GetAllFavouritePhotosUseCase
import com.abdulqohar.bicyclephotos.domain.usecase.GetBicyclePhotosUseCase
import com.abdulqohar.bicyclephotos.domain.usecase.SaveFavouriteImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesNetworkPort(bicyclePhotosService: BicyclePhotosService, bicycleItemsDao: BicycleItemsDao): BicyclePhotosRepository =
        BicyclePhotosRepositoryImpl(bicyclePhotosService, bicycleItemsDao)


    @Provides
    @Singleton
    fun providesGetBicyclePhotosUseCase(bicyclePhotosRepository: BicyclePhotosRepository): GetBicyclePhotosUseCase =
        GetBicyclePhotosUseCase(bicyclePhotosRepository)

    @Provides
    @Singleton
    fun providesCheckIfFavouritePhotoExistsUseCase(bicyclePhotosRepository: BicyclePhotosRepository): CheckIsFavouritePhotoExists = CheckIsFavouritePhotoExists(
        bicyclePhotosRepository
    )

    @Provides
    @Singleton
    fun providesGetAllFavouritePhotosUseCase(bicyclePhotosRepository: BicyclePhotosRepository): GetAllFavouritePhotosUseCase = GetAllFavouritePhotosUseCase(
        bicyclePhotosRepository
    )

    @Provides
    @Singleton
    fun providesSaveFavouriteImageUseCase(bicyclePhotosRepository: BicyclePhotosRepository): SaveFavouriteImageUseCase = SaveFavouriteImageUseCase(
        bicyclePhotosRepository
    )
}