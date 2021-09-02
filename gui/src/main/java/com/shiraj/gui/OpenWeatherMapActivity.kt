package com.shiraj.gui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.shiraj.base.activity.BaseActivity
import com.shiraj.gui.databinding.ActivityOpenWeatherMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class OpenWeatherMapActivity : BaseActivity() {

    override val layoutResId: Int
        get() = R.layout.activity_open_weather_map

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = ActivityOpenWeatherMapBinding::inflate

    override val binding: ActivityOpenWeatherMapBinding
        get() = super.binding as ActivityOpenWeatherMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}