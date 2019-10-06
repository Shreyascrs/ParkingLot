package com.thoughtworks.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestAttendant {

    @Test // TODO - update the name of the test.
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInSecondParkinglot() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> observer = new ArrayList<>();
        //observer.add(owner); // TODO - why we need the owner.
        Object vehicle = new Object();

        ParkingLot firstParkingLot = new ParkingLot(1, observer);
        ParkingLot secondParkingLot = new ParkingLot(2, observer);

        Attendant attendant = new Attendant(firstParkingLot, secondParkingLot);
        attendant.park(vehicle);

        assertEquals(vehicle, secondParkingLot.unPark(vehicle));
        assertThrows(VehicleNotFoundException.class,()->firstParkingLot.unPark(vehicle));
    }

    @Test
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInMostCapcity() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> observer = new ArrayList<>();
        observer.add(owner);
        Object vehicle = new Object();
        ParkingLot firstParkingLot = new ParkingLot(2, observer);
        ParkingLot secondParkingLot = new ParkingLot(1, observer);

        Attendant attendant = new Attendant(firstParkingLot, secondParkingLot);
        attendant.park(vehicle);

        assertEquals(vehicle, firstParkingLot.unPark(vehicle));
    }

    @Test
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInMostAvailableSpace() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> observer = new ArrayList<>();
        observer.add(owner);
        Object vehicle = new Object();
        ParkingLot firstParkingLot = new ParkingLot(3, observer);
        ParkingLot secondParkingLot = new ParkingLot(1, observer);

        SecondAttendant attendant = new SecondAttendant(firstParkingLot, secondParkingLot);
        attendant.park(vehicle);

        assertEquals(vehicle, firstParkingLot.unPark(vehicle));
    }



}
