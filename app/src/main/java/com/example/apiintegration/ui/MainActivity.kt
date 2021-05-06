package com.example.apiintegration.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.apiintegration.data.PlanetaryData
import com.example.apiintegration.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PlanetaryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPlanetaryData()

        lifecycleScope.launchWhenCreated {
            viewModel.planetaryData.collect {
                when (it) {
                    is Resource.Success -> it.data?.let { it1 -> setUI(it1) }
                    is Resource.Error -> Log.d(TAG, "onCreate: $it")
                    is Resource.Loading -> Log.d(TAG, "onCreate: $it")
                    is Resource.Empty -> Log.d(TAG, "onCreate: $it")
                }
            }
        }

    }

    private fun setUI(planetaryData: PlanetaryData) {
        binding.titleTextView.text = planetaryData.title
        binding.explanationTextView.text= planetaryData.explanation
    }
}