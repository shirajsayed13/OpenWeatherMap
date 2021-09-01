package com.shiraj.gui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.shiraj.base.fragment.BaseFragment
import com.shiraj.gui.databinding.FragmentWeatherInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherInfoFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_weather_info

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
        get() = FragmentWeatherInfoBinding::inflate

    override val binding: FragmentWeatherInfoBinding
        get() = super.binding as FragmentWeatherInfoBinding

    private val viewModel: WeatherInfoViewModel by viewModels()

    override fun onInitView() {
        TODO("Not yet implemented")
    }
}