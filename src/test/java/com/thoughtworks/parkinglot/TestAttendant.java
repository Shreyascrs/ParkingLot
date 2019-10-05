package com.thoughtworks.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInMostCapcity() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> observer = new ArrayList<>();
        observer.add(owner);

        ParkingLot firstParkingLot = new ParkingLot(1, observer);


        ParkingLot secondParkingLot = new ParkingLot(2, observer);

        Attendant attendant=new Attendant(firstParkingLot,secondParkingLot);

    }

}
