package com.shiraj.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingItem(
    val dt: Int,
    val dtTxt: String,
    val main: Main
) : Parcelable

@Parcelize
data class Main(
    val temp: Double
) : Parcelable