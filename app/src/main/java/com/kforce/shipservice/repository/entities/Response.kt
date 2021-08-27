package com.kforce.shipservice.repository.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model class converting to json from the network api
 */
class Response {
    @JsonClass(generateAdapter = true)
    class Ship (
        @Json(name = "shipName") val shipName: String,
        @Json(name = "shipFacts") val shipFacts: ShipFacts
    )

    @JsonClass(generateAdapter = true)
    class ShipFacts (
        @Json(name = "passengerCapacity") val passengerCapacity: String,
        @Json(name = "crew") val crew: String,
        @Json(name = "inauguralDate") val inauguralDate: String,
    )
}