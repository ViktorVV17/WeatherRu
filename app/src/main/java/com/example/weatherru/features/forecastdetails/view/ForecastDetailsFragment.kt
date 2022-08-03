package com.example.weatherru.features.forecastdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherru.databinding.FragmentForecastDetailsBinding
import com.example.weatherru.features.forecastdetails.adapter.ForecastAdapter
import com.example.weatherru.features.forecastdetails.viewmodel.ForecastDetailsViewModel

class ForecastDetailsFragment : Fragment() {
    private lateinit var binding: FragmentForecastDetailsBinding
    private val args: ForecastDetailsFragmentArgs by navArgs()
    private val viewModel: ForecastDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(args.item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            item = args.item
            forecastList.adapter = ForecastAdapter()
        }
        observeForecastList()
        return binding.root
    }

    private fun observeForecastList() {
        viewModel.forecastList.observe(viewLifecycleOwner) {
            val adapter = binding.forecastList.adapter as? ForecastAdapter
            adapter?.submitList(it)
        }
    }
}