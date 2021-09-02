package com.shiraj.gui.weatherinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.shiraj.base.failure
import com.shiraj.base.fragment.BaseFragment
import com.shiraj.base.observe
import com.shiraj.core.webservice.WebServiceFailure
import com.shiraj.gui.AppToast
import com.shiraj.gui.R
import com.shiraj.gui.databinding.FragmentWeatherInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        viewModel.apply {
            failure(failure, ::handleFailure)
            observe(feedItems, { println("CHECK THIS LISTING $it") })
            loadListing()
        }
    }

    private fun handleFailure(e: Exception?) {
        Timber.v("handleFailure: IN")
        Timber.e(e)
        when (e) {
            is WebServiceFailure.NoNetworkFailure -> showErrorToast("No internet connection!")
            is WebServiceFailure.NetworkTimeOutFailure, is WebServiceFailure.NetworkDataFailure -> showErrorToast(
                "Internal server error!"
            )
            else -> showErrorToast("Unknown error occurred!")
        }
        Timber.v("handleFailure: OUT")
    }

    private fun Fragment.showErrorToast(msg: String) {
        AppToast.show(requireContext(), msg, Toast.LENGTH_SHORT)
    }
}