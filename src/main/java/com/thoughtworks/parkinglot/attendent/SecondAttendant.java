package com.thoughtworks.parkinglot.attendent;

import com.thoughtworks.parkinglot.ParkingLot;

public class SecondAttendant implements IAttendant { // TODO - duplication
    private final ParkingLot firstParkingLot;
    private final ParkingLot secondParkingLot;

    public SecondAttendant(ParkingLot firstParkingLot, ParkingLot secondParkingLot) {
        this.firstParkingLot = firstParkingLot;
        this.secondParkingLot = secondParkingLot;
    }

    public void park(Object vehicle) throws Exception {
        if (firstParkingLot.getAvailableSpace() >= secondParkingLot.getAvailableSpace()) {
            firstParkingLot.park(vehicle);
        } else {
            secondParkingLot.park(vehicle);
        }

    }
}
