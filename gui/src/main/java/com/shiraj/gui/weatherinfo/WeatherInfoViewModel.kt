package com.shiraj.gui.weatherinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shiraj.base.viewmodel.BaseViewModel
import com.shiraj.core.usecase.GetWeatherInfoUseCase
import com.shiraj.core.model.WeatherInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase
) : BaseViewModel() {

    private val _feedItems: MutableLiveData<List<WeatherInfoModel>> by lazy { MutableLiveData() }
    internal val feedItems: LiveData<List<WeatherInfoModel>> = _feedItems

    internal fun loadWeatherInfo() {
        Timber.d("loadWeatherInfo: ")
        launchUseCase {
            _feedItems.postValue(getWeatherInfoUseCase())
        }
    }
}