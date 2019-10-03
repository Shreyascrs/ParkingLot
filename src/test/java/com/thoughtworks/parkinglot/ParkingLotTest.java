package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.EmptyParkingLotException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isParkingLotFull());
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        parkingLot.park(new Object());

        Throwable exception = assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
        assertEquals(exception.getMessage(), "parking Lot is Full");
    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object vehicle = new Object();

        parkingLot.park(vehicle);

        Throwable exception = assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(vehicle));
        assertEquals(exception.getMessage(), "vehicle already parked");
    }

    @Test
    void givenParkingLotWithNoVehicals_WhenUnPark_ThenMustNotUnPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(0);

        Object vehicle = new Object();

        Throwable exception = assertThrows(EmptyParkingLotException.class, () -> parkingLot.unPark(vehicle));
        assertEquals(exception.getMessage(), "parkingLot is empty");
    }

    @Test
    void givenParkingLotWithOneVehicleAndGivenOtherVehicleToUnpark_WhenUnPark_ThenMustNotUnPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        Throwable exception = assertThrows(VehicleNotFoundExcepttion.class, () -> parkingLot.unPark(new Object()));
        assertEquals(exception.getMessage(), "vehicle not found");
    }
}
