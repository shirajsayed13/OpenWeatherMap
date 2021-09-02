package com.shiraj.network


import com.shiraj.core.ListingItem
import com.shiraj.core.Main
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

internal fun TemperatureListingResponse.MainList.toMainList() = ListingItem(
    dt = dt,
    dtTxt = dtTxt,
    main = main.toMainTemperature()
)

internal fun TemperatureListingResponse.MainList.Main.toMainTemperature() = Main(
    temp = temp
)

@JsonClass(generateAdapter = true)
data class TemperatureListingResponse(
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


