package com.shiraj.core.webservice

import com.shiraj.core.model.WeatherInfoModel


interface WeatherInfoWS {
    suspend fun getWeatherInfoWS(): List<WeatherInfoModel>
}