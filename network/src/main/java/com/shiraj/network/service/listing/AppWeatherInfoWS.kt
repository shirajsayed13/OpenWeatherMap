package com.shiraj.network.service.listing

import com.shiraj.core.model.WeatherInfoModel
import com.shiraj.core.webservice.WeatherInfoWS
import com.shiraj.network.networkCall
import com.shiraj.network.response.toMainList
import javax.inject.Inject

internal class AppWeatherInfoWS @Inject constructor(
    private val weatherInfoWebService: RetrofitWeatherInfoWebService
) : WeatherInfoWS {

    override suspend fun getWeatherInfoWS(): List<WeatherInfoModel> = networkCall(
        { weatherInfoWebService.getWeatherInfoWebService("Bengaluru", "9b8cb8c7f11c077f8c4e217974d9ee40") },
        { response -> response.mainList.map { it.toMainList() } }
    )
}