package com.shiraj.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherInfoModel(
    val dt: Int,
    val dtTxt: String,
    val main: Main
) : Parcelable

@Parcelize
data class Main(
    val temp: Double
) : Parcelable