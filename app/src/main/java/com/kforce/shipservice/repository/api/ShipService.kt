package com.kforce.shipservice.repository.api

import com.kforce.shipservice.repository.entities.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShipService {
    @GET("{name}")
    suspend fun getShipInfo(
        @Path("name") name: String
    ): Response.Ship
}