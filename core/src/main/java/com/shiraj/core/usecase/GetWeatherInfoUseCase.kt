package com.shiraj.core.usecase

import com.shiraj.core.webservice.WeatherInfoWS
import com.shiraj.core.model.WeatherInfoModel
import javax.inject.Inject

class GetWeatherInfoUseCase @Inject constructor(
    private val weatherInfoWS: WeatherInfoWS
) {

    suspend operator fun invoke(): List<WeatherInfoModel> {
        return weatherInfoWS.getWeatherInfoWS()
    }
}