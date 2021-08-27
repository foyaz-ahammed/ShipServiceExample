package com.kforce.shipservice.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.kforce.shipservice.databinding.ActivityMainBinding
import com.kforce.shipservice.repository.entities.LoadResult
import com.kforce.shipservice.repository.entities.ShipInfo
import com.kforce.shipservice.viewmodels.MainScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Main screen activity
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Update UI from the data
        viewModel.shipInfo.observe(this) {
            if(it != null) {
                updateShipInfoViews(it)
            }
        }

        //Set visibility according to the loading status
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it == LoadResult.LOADING
            binding.shipInfoViews.isVisible = it == LoadResult.SUCCESS
            binding.errorShip.isVisible = it == LoadResult.FAILURE
        }

        //Add click actions
        binding.sky.setOnClickListener { viewModel.fetchSkyShipData() }
        binding.bliss.setOnClickListener { viewModel.fetchBlissShipData() }
        binding.escape.setOnClickListener { viewModel.fetchEscapeShipData() }
    }

    //Update ship info views
    private fun updateShipInfoViews(item: ShipInfo) {
        binding.apply {
            shipName.text = item.shipName
            passengerCapacity.text = item.passengerCapacity
            crew.text = item.crew
            inauguralDate.text = item.inauguralDate
        }
    }
}