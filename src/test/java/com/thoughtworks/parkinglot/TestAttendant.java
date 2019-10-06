package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.attendent.FirstAttendant;
import com.thoughtworks.parkinglot.attendent.SecondAttendant;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestAttendant {

    @Test
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInMaximumCapacity() throws Exception {
        List<INotification> observer = new ArrayList<>();
        Object vehicle = new Object();

        ParkingLot firstParkingLot = new ParkingLot(1, observer);
        ParkingLot secondParkingLot = new ParkingLot(2, observer);

        FirstAttendant firstAttendant = new FirstAttendant(firstParkingLot, secondParkingLot);
        firstAttendant.park(vehicle);

        assertEquals(vehicle, secondParkingLot.unPark(vehicle));
        assertThrows(VehicleNotFoundException.class,()->firstParkingLot.unPark(vehicle));
    }

    @Test
    void givenAttendantWithTwoParkingLot_WhenPark_ThenMustParkInMostCapacity() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> observer = new ArrayList<>();
        observer.add(owner);
        Object vehicle = new Object();
        ParkingLot firstParkingLot = new ParkingLot(2, observer);
        ParkingLot secondParkingLot = new ParkingLot(1, observer);

        FirstAttendant firstAttendant = new FirstAttendant(firstParkingLot, secondParkingLot);
        firstAttendant.park(vehicle);

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
        assertThrows(VehicleNotFoundException.class,()->secondParkingLot.unPark(vehicle));
    }


}
