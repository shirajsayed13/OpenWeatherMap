package com.shiraj.network.service.listing

import com.shiraj.network.response.TemperatureListingResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitListingWebService {

    @GET("forecast")
    suspend fun getListing(
        @Query("q", encoded = true) query: String,
        @Query("APPID", encoded = true) appId: String,
    ): TemperatureListingResponse
}