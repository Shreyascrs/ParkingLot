package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot {

    private int capacity;


    List<INotification> notifiers;
    Set<Object> vehicles = new HashSet<>();

    public ParkingLot(int capacity, List<INotification> owner) { // TODO - Both owner and security guard can also come right in the beginning.
        this.capacity = capacity;
        notifiers = owner;
    }


    public void park(Object object) throws ParkingLotFullException, VehicleAlreadyParkedException {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException("parking Lot is Full"); // TODO - strings
        }

        if (isParked(object)) {
            throw new VehicleAlreadyParkedException("vehicle already parked");
        }
        vehicles.add(object);
        if (isFull(capacity)) {

            for (INotification notifier : notifiers) { // TODO - no abbreviations. Name loops, like we name conditions.
                notifier.notifyWhenFull();
            }
        }
    }

    private boolean isFull(int capacity) {
        return vehicles.size() == capacity;
    }

    public Object unPark(Object vehicle) throws VehicleNotFoundExcepttion {

        if (isPresent(vehicle)) {
            throw new VehicleNotFoundExcepttion("vehicle not found");
        }
        if (isFull(capacity)) {
            vehicles.remove(vehicle);

            for (INotification notifier : notifiers) {
                notifier.notifyWhenEmpty();
            }
            return vehicle;
        }
        vehicles.remove(vehicle);
        return vehicle;
    }

    private boolean isPresent(Object vehicle) {
        return !(vehicles.contains(vehicle));
    }

    private boolean isParked(Object object) {
        return vehicles.contains(object);
    }

    protected boolean isParkingLotFull() {
        return vehicles.size() >= capacity;
    }

    public void register(INotification object) {

    }
}
