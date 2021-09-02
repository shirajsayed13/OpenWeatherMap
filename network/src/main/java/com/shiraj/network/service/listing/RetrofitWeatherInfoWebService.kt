package com.shiraj.network.service.listing

import com.shiraj.network.response.WeatherInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitWeatherInfoWebService {

    @GET("forecast")
    suspend fun getWeatherInfoWebService(
        @Query("q", encoded = true) query: String,
        @Query("APPID", encoded = true) appId: String,
    ): WeatherInfoResponse
}