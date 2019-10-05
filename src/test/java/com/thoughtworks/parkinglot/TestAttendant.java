package com.thoughtworks.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestAttendant {

    class DummyOwner implements INotification {

        @Override
        public void notifyWhenFull() {

        }

        @Override
        public void notifyWhenEmpty() {

        }
    }

    @Test
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInSecondParkinglot() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> observer = new ArrayList<>();
        observer.add(owner);
        Object vehicle = new Object();

        ParkingLot firstParkingLot = new ParkingLot(1, observer);
        ParkingLot secondParkingLot = new ParkingLot(2, observer);
        Attendant attendant = new Attendant(firstParkingLot, secondParkingLot);

        attendant.park(vehicle);

        assertEquals(vehicle,secondParkingLot.unPark(vehicle));

    }

    @Test
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInMostCapcity() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> observer = new ArrayList<>();
        observer.add(owner);
        Object vehicle=new Object();
        ParkingLot firstParkingLot = new ParkingLot(2, observer);
        ParkingLot secondParkingLot = new ParkingLot(1, observer);

        Attendant attendant = new Attendant(firstParkingLot, secondParkingLot);
        attendant.park(vehicle);

        assertEquals(vehicle,firstParkingLot.unPark(vehicle));
    }

}
