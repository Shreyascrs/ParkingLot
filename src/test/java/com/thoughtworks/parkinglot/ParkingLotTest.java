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
    void givenParkingLotWithOneVehicleAndGivenOtherVehicleToUnpark_WhenUnPark_ThenMustNotUnPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        Throwable exception = assertThrows(VehicleNotFoundExcepttion.class, () -> parkingLot.unPark(new Object()));
        assertEquals(exception.getMessage(), "vehicle not found");
    }

    void givenParkingLotWithOneVehicle_WhenUnPark_ThenMustUnPark() throws ParkingLotFullException, VehicleAlreadyParkedException, VehicleNotFoundExcepttion {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithOneVehicleMustUnparkAndOnceAgainUnpark_WhenUnPark_ThenMustThrowException() throws ParkingLotFullException, VehicleAlreadyParkedException, VehicleNotFoundExcepttion {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        Throwable exception = assertThrows(VehicleNotFoundExcepttion.class, () -> parkingLot.unPark(vehicle));
        assertEquals(exception.getMessage(), "vehicle not found");
    }

    @Test
    void givenParkingLotWithTwoVehicles_WhenUnpark_ThenUnpark() throws VehicleAlreadyParkedException, ParkingLotFullException, VehicleNotFoundExcepttion {
        ParkingLot parkingLot = new ParkingLot(3);
        Object vehicle1 = new Object();
        Object vehicle2 = new Object();

        parkingLot.park(vehicle1);
        parkingLot.park(vehicle2);

        assertEquals(vehicle1, parkingLot.unPark(vehicle1));
        assertEquals(vehicle2, parkingLot.unPark(vehicle2));
    }
}
