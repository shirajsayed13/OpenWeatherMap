package com.shiraj.core.usecase

import com.shiraj.core.webservice.ListingWebService
import com.shiraj.core.model.WeatherInfoModel
import javax.inject.Inject

class GetWeatherInfoUseCase @Inject constructor(
    private val listingWebService: ListingWebService
) {

    suspend operator fun invoke(): List<WeatherInfoModel> {
        return listingWebService.getListItems()
    }
}