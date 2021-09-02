package com.shiraj.gui.weatherinfo

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shiraj.base.failure
import com.shiraj.base.fragment.BaseFragment
import com.shiraj.base.observe
import com.shiraj.core.model.WeatherInfoModel
import com.shiraj.core.webservice.WebServiceFailure
import com.shiraj.gui.AppToast
import com.shiraj.gui.R
import com.shiraj.gui.databinding.FragmentWeatherInfoBinding
import com.shiraj.gui.getDate
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class WeatherInfoFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_weather_info

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
        get() = FragmentWeatherInfoBinding::inflate

    override val binding: FragmentWeatherInfoBinding
        get() = super.binding as FragmentWeatherInfoBinding

    @Inject
    lateinit var weatherInfoAdapter: WeatherInfoAdapter

    private val viewModel: WeatherInfoViewModel by viewModels()

    override fun onInitView() {
        binding.apply {
            llLoading.visibility = VISIBLE
            llMain.visibility = GONE
            llError.visibility = GONE
        }

        ObjectAnimator.ofPropertyValuesHolder(
            PropertyValuesHolder.ofFloat("scaleX", 0.9f),
            PropertyValuesHolder.ofFloat("scaleY", 0.9f)
        ).apply {
            duration = 5_000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            start()
        }

        binding.apply {
            ivRotatingLogo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.clockwise))
            btnRetry.setOnClickListener {
                viewModel.loadWeatherInfo()
            }
        }

        viewModel.apply {
            failure(failure, ::handleFailure)
            observe(feedItems, ::showWeatherInfo)
            loadWeatherInfo()
        }
    }

    private fun showWeatherInfo(weatherInfo: List<WeatherInfoModel>) {
        val weatherInfoViews = mutableListOf<WeatherInfoView>()
        val temp = "${((weatherInfo[0].main.temp - 273.15).toInt())}\u2103"
        binding.tvTemp.text = temp
        weatherInfo.forEach {
            val d = it.dtTxt.split(" ")[0]
            weatherInfoViews.add(WeatherInfoView(getDate(d), (it.main.temp - 273.15).toInt()))
        }

        with(BottomSheetDialog(binding.root.context, R.style.AppBottomSheetDialogTheme)) {
            setContentView(R.layout.bottomsheet_temperatures)

            findViewById<RecyclerView>(R.id.rvWeatherInfo)?.apply {
                this.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = weatherInfoAdapter
                weatherInfoAdapter.info = weatherInfoViews
            }
            show()
            binding.apply {
                ivRotatingLogo.clearAnimation()
                llLoading.visibility = GONE
                llMain.visibility = VISIBLE
                llError.visibility = GONE
            }
        }
    }

    private fun showErrorScreen() {
        binding.apply {
            ivRotatingLogo.clearAnimation()
            llLoading.visibility = GONE
            llMain.visibility = GONE
            llError.visibility = VISIBLE
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
            else -> {
                showErrorScreen()
                showErrorToast("Unknown error occurred!")
            }
        }
        Timber.v("handleFailure: OUT")
    }

    private fun Fragment.showErrorToast(msg: String) {
        AppToast.show(requireContext(), msg, Toast.LENGTH_SHORT)
    }
}