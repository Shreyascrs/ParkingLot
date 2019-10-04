package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;


class ParkingLotTest {

    Owner owner = new Owner();

    ParkingLot parkingLotm = new ParkingLot(1, new Owner());

    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isParkingLotFull());
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, owner);

        parkingLot.park(new Object());

        Throwable exception = assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
        assertEquals(exception.getMessage(), "parking Lot is Full");
    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicle = new Object();

        parkingLot.park(vehicle);

        Throwable exception = assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(vehicle));
        assertEquals(exception.getMessage(), "vehicle already parked");
    }

    @Test
    void givenParkingLotWithOneVehicleAndGivenOtherVehicleToUnpark_WhenUnPark_ThenMustNotUnPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        Throwable exception = assertThrows(VehicleNotFoundExcepttion.class, () -> parkingLot.unPark(new Object()));
        assertEquals(exception.getMessage(), "vehicle not found");
    }

    @Test
    void givenParkingLotWithOneVehicle_WhenUnPark_ThenMustUnPark() throws ParkingLotFullException, VehicleAlreadyParkedException, VehicleNotFoundExcepttion {
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithOneVehicleMustUnparkAndOnceAgainUnpark_WhenUnPark_ThenMustThrowException() throws ParkingLotFullException, VehicleAlreadyParkedException, VehicleNotFoundExcepttion {
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        Throwable exception = assertThrows(VehicleNotFoundExcepttion.class, () -> parkingLot.unPark(vehicle));
        assertEquals(exception.getMessage(), "vehicle not found");
    }

    class DummyOwner extends Owner {

        private int count=0;
        private String message;

        @Override
        public void notify(String message) {
            this.message = message;
            count++;
        }
    }

    @Test
    void givenParkingLotWithTwoVehicles_WhenUnpark_ThenUnpark() throws VehicleAlreadyParkedException, ParkingLotFullException, VehicleNotFoundExcepttion {
        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(3, owner);
        Object vehicle1 = new Object();
        Object vehicle2 = new Object();

        parkingLot.park(vehicle1);
        parkingLot.park(vehicle2);

        assertEquals(vehicle1, parkingLot.unPark(vehicle1));
        assertEquals(vehicle2, parkingLot.unPark(vehicle2));

    }

    @Test
    void givenParkingLotfull_WhenPark_ThenOwnerMustGetMessage() throws VehicleAlreadyParkedException, ParkingLotFullException {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        parkingLot.park(new Object());
        assertEquals("parking lot is full", owner.message);

    }

    @Test
    void givenParkingLotfull_WhenPark_ThenhowManyTimesMethodCalled() throws VehicleAlreadyParkedException, ParkingLotFullException {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        parkingLot.park(new Object());
        assertEquals(1, owner.count);

    }

    @Test
    void givenParkingLotfullParkedAndUnParked_WhenPark_ThenhowManyTimesMethodCalled() throws VehicleAlreadyParkedException, ParkingLotFullException, VehicleNotFoundExcepttion {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vechicle=new Object();

        parkingLot.park(vechicle);
        parkingLot.unPark(vechicle);
        parkingLot.park(vechicle);

        assertEquals(2, owner.count);

    }
}
