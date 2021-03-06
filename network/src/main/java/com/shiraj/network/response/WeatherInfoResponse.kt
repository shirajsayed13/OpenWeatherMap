package com.shiraj.network.response


import com.shiraj.core.model.WeatherInfoModel
import com.shiraj.core.model.Main
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

internal fun WeatherInfoResponse.MainList.toMainList() = WeatherInfoModel(
    dt = dt,
    dtTxt = dtTxt,
    main = main.toMainTemperature()
)

internal fun WeatherInfoResponse.MainList.Main.toMainTemperature() = Main(
    temp = temp
)

@JsonClass(generateAdapter = true)
data class WeatherInfoResponse(
    @Json(name = "cnt")
    val cnt: Int,
    @Json(name = "cod")
    val cod: String,
    @Json(name = "list")
    val mainList: List<MainList>
) {
    @JsonClass(generateAdapter = true)
    data class MainList(
        @Json(name = "dt")
        val dt: Int,
        @Json(name = "dt_txt")
        val dtTxt: String,
        @Json(name = "main")
        val main: Main
    ) {
        @JsonClass(generateAdapter = true)
        data class Main(
            @Json(name = "temp")
            val temp: Double
        )
    }
}


