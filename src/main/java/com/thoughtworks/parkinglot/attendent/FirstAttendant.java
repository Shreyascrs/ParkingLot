package com.thoughtworks.parkinglot.attendent;

import com.thoughtworks.parkinglot.ParkingLot;

public class FirstAttendant implements IAttendant {

    private final ParkingLot firstParkingLot;
    private final ParkingLot secondParkingLot;

    public FirstAttendant(ParkingLot firstParkingLot, ParkingLot secondParkingLot) {
        this.firstParkingLot = firstParkingLot;
        this.secondParkingLot = secondParkingLot;
    }

    public void park(Object vehicle) throws Exception {
        if (firstParkingLot.getCapacity() < secondParkingLot.getCapacity()) {
            secondParkingLot.park(vehicle);
        } else {
            firstParkingLot.park(vehicle);
        }
    }
}
