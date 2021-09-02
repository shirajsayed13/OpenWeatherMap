package com.shiraj.gui.weatherinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiraj.gui.databinding.TileDailyTemperatureBinding
import javax.inject.Inject
import kotlin.properties.Delegates

class WeatherInfoAdapter @Inject constructor() :
    RecyclerView.Adapter<WeatherInfoAdapter.WeatherInfoVH>() {

    var info: List<WeatherInfoView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    class WeatherInfoVH(private val binding: TileDailyTemperatureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(info: WeatherInfoView) {
            binding.tvDay.text = info.day
            binding.tvTemp.text = "${info.temperature} C"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherInfoVH(
        TileDailyTemperatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: WeatherInfoVH, position: Int) =
        holder.bind(info[position])

    override fun getItemCount() = info.size
}