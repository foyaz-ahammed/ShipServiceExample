package com.kforce.shipservice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kforce.shipservice.helpers.BLISS
import com.kforce.shipservice.helpers.ESCAPE
import com.kforce.shipservice.helpers.SKY
import com.kforce.shipservice.repository.ShipRepository
import com.kforce.shipservice.repository.entities.DataResult
import com.kforce.shipservice.repository.entities.LoadResult
import com.kforce.shipservice.repository.entities.ShipInfo
import kotlinx.coroutines.launch

/**
 * [ViewModel] used in MainActivity
 */
class MainScreenViewModel(private val repository: ShipRepository): ViewModel() {
    private val _shipInfo = MutableLiveData<ShipInfo>()
    private val _loading = MutableLiveData(LoadResult.Initial)

    val shipInfo: LiveData<ShipInfo>
        get() = _shipInfo
    val loading: LiveData<LoadResult>
        get() = _loading

    /**
     * Fetch data and update live data
     */
    private fun fetchData(name: String) {
        viewModelScope.launch {
            _loading.value = LoadResult.LOADING

            when(val result = repository.getShipInfo(name)) {
                is DataResult.Success -> {
                    _shipInfo.value = result.data
                    _loading.value = LoadResult.SUCCESS
                }
                is DataResult.Failure -> {
                    _shipInfo.value = null
                    _loading.value = LoadResult.FAILURE
                }
            }
        }
    }

    fun fetchSkyShipData() = fetchData(SKY)
    fun fetchBlissShipData() = fetchData(BLISS)
    fun fetchEscapeShipData() = fetchData(ESCAPE)
}