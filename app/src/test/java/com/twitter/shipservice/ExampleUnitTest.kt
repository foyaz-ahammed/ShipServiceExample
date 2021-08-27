package com.twitter.shipservice

import com.kforce.shipservice.repository.entities.Response
import com.kforce.shipservice.repository.entities.ShipInfo
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    /**
     * Test [Response.Ship] -> [ShipInfo] conversions
     * */
    @Test
    fun convertResponseTest() {
        assertShipInfo_Ship("shipName1", "passengerCapacity1", "crew1", "inauguralDate1")
        assertShipInfo_Ship("shipName2", "passengerCapacity2", "crew2", "inauguralDate2")
        assertShipInfo_Ship("shipName3", "passengerCapacity3", "crew3", "inauguralDate3")
        assertShipInfo_Ship("shipName4", "passengerCapacity4", "crew4", "inauguralDate4")
        assertShipInfo_Ship("shipName5", "passengerCapacity5", "crew5", "inauguralDate5")
    }

    private fun assertShipInfo_Ship(shipName: String, passengerCapacity: String, crew: String, inauguralDate: String)  {
        val ship = Response.Ship(shipName, Response.ShipFacts(passengerCapacity, crew, inauguralDate))
        val shipInfo = ShipInfo(shipName, passengerCapacity, crew, inauguralDate)
        assertEquals(shipInfo, ShipInfo(ship))
    }
}