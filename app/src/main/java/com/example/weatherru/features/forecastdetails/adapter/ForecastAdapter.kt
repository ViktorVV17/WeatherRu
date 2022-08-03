package com.example.weatherru.features.forecastdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherru.databinding.CitiesListItemBinding
import com.example.weatherru.features.forecastdetails.data.item.ForecastListItem

class ForecastAdapter : ListAdapter<ForecastListItem, ForecastViewHolder>(callback) {

    companion object {
        private val callback = object : DiffUtil.ItemCallback<ForecastListItem>() {

            override fun areItemsTheSame(
                oldItem: ForecastListItem,
                newItem: ForecastListItem
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ForecastListItem,
                newItem: ForecastListItem
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ForecastViewHolder.from(parent)

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ForecastViewHolder(private val binding: CitiesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(parent: ViewGroup) = CitiesListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ForecastViewHolder(this) }
    }

    fun bind(item: ForecastListItem) {

        with(binding) {
            title.text = item.time
            temperature.text = item.temperature
        }
    }
}