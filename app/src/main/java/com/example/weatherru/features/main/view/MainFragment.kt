package com.example.weatherru.features.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherru.databinding.FragmentMainBinding
import com.example.weatherru.features.main.adapter.CitiesListAdapter
import com.example.weatherru.features.main.data.item.CitiesListItem
import com.example.weatherru.features.main.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
            citiesList.adapter = CitiesListAdapter {
                showForecastDetails(it)
            }
        }
        observeCitiesList()
        return binding.root
    }

    private fun observeCitiesList() {
        viewModel.citiesList.observe(viewLifecycleOwner) {
            val adapter = binding.citiesList.adapter as? CitiesListAdapter
            adapter?.submitList(it)
        }
    }

    private fun showForecastDetails(item: CitiesListItem) {
        val action = MainFragmentDirections.actionMainToForecastDetails(item)
        findNavController().navigate(action)
    }
}