package com.abdulqohar.bicyclephotos.data.remote

import com.abdulqohar.bicyclephotos.data.remote.model.BicyclePhotosDto
import com.abdulqohar.bicyclephotos.util.Routes.Companion.BASE_URL
import retrofit2.http.GET
import retrofit2.http.Query

interface BicyclePhotosService {

    @GET(BASE_URL)
    suspend fun getBicyclePhotos(@Query("key") apiKey: String,
                                 @Query("q") query: String,
                                 @Query("image_type") imageType: String): BicyclePhotosDto

}