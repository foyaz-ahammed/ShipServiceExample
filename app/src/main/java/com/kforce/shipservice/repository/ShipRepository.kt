package com.kforce.shipservice.repository

import android.util.Log
import com.kforce.shipservice.repository.api.ShipService
import com.kforce.shipservice.repository.entities.DataResult
import com.kforce.shipservice.repository.entities.ShipInfo
import java.lang.Exception

/**
 * Repository class to load data from the third party web service
 */
class ShipRepository(private val apiService: ShipService) {
    companion object {
        const val TAG = "ShipRepository"
    }

    suspend fun getShipInfo(name: String): DataResult<ShipInfo> =
        try {
            val ship = apiService.getShipInfo(name)
            DataResult.Success(ShipInfo(ship))
        } catch (e: Exception) {
            Log.w(TAG, e.toString())
            DataResult.Failure(e)
        }
}