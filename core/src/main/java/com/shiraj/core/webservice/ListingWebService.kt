package com.shiraj.core.webservice

import com.shiraj.core.model.WeatherInfoModel


interface ListingWebService {
    suspend fun getListItems(): List<WeatherInfoModel>
}