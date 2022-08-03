package com.example.weatherru.features.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherru.databinding.CitiesListItemBinding
import com.example.weatherru.features.main.data.item.CitiesListItem

class CitiesListAdapter(private val itemClick: (CitiesListItem) -> Unit) :
    ListAdapter<CitiesListItem, CitiesListViewHolder>(callback) {

    companion object {
        private val callback = object : DiffUtil.ItemCallback<CitiesListItem>() {

            override fun areItemsTheSame(
                oldItem: CitiesListItem,
                newItem: CitiesListItem
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CitiesListItem,
                newItem: CitiesListItem
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CitiesListViewHolder.from(parent)

    override fun onBindViewHolder(holder: CitiesListViewHolder, position: Int) {
        holder.bind(getItem(position), itemClick)
    }
}

class CitiesListViewHolder(private val binding: CitiesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(parent: ViewGroup) = CitiesListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .run { CitiesListViewHolder(this) }
    }

    fun bind(item: CitiesListItem, itemClick: (CitiesListItem) -> Unit) {
        with(binding) {
            title.text = item.name
            temperature.text = item.temperature
            root.setOnClickListener { itemClick(item) }
        }
    }
}