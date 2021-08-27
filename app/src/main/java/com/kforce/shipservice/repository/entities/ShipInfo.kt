package com.kforce.shipservice.repository.entities

/**
 * Ship Information Class
 * Ship name, Passenger Capacity, Crew, Inaugural Date
 */
class ShipInfo(val shipName: String, val passengerCapacity: String, val crew: String, val inauguralDate: String) {
    constructor(ship: Response.Ship)
            : this(ship.shipName, ship.shipFacts.passengerCapacity, ship.shipFacts.crew, ship.shipFacts.inauguralDate)

    override fun equals(other: Any?): Boolean {
        if(other is ShipInfo) {
            return shipName == other.shipName && passengerCapacity == other.passengerCapacity && crew == other.crew && inauguralDate == other.inauguralDate
        }
        return false
    }

    override fun hashCode(): Int {
        var result = shipName.hashCode()
        result = 31 * result + passengerCapacity.hashCode()
        result = 31 * result + crew.hashCode()
        result = 31 * result + inauguralDate.hashCode()
        return result
    }
}