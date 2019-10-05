package com.thoughtworks.parkinglot;

public class Attendant {

    private final ParkingLot firstParkingLot;
    private final ParkingLot secondParkingLot;

    public Attendant(ParkingLot firstParkingLot, ParkingLot secondParkingLot) {
        this.firstParkingLot = firstParkingLot;
        this.secondParkingLot = secondParkingLot;
    }

    public void park(Object vehicle) throws Exception {
        secondParkingLot.park(vehicle);
    }
}
