package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.PersonNotSubscribed;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO - make it green
public class ParkingLot {

    private int capacity;

    List<INotification> notifiers;
    Set<Object> vehicles = new HashSet<>();

    public ParkingLot(int capacity, List<INotification> owner) {
        this.capacity = capacity;
        notifiers = owner;
    }


    public void park(Object object) throws ParkingLotFullException, VehicleAlreadyParkedException {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException();
        }

        if (isParked(object)) {
            throw new VehicleAlreadyParkedException();
        }
        vehicles.add(object);
        if (isFull()) {
            notifyWhenFull();
        }
    }

    private void notifyWhenFull() {
        for (INotification notifier : notifiers) {
            notifier.notifyWhenFull();
        }
    }

    private boolean isFull() {
        return vehicles.size() == this.capacity;
    }

    public Object unPark(Object vehicle) throws VehicleNotFoundException {

        if (isPresent(vehicle)) {
            throw new VehicleNotFoundException();
        }
        vehicles.remove(vehicle);

        if (isCapacityFull()) {
            notifyWhenEmpty();
        }
        return vehicle;
    }

    private boolean isCapacityFull() {
        return notifiers.size()==capacity-1;
    }

    private void notifyWhenEmpty() {
        for (INotification notifier : notifiers) {
            notifier.notifyWhenEmpty();
        }
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

    public void subscribe(INotification notifier) {
        notifiers.add(notifier);
    }

    public void unSubscribe(INotification notifier) throws PersonNotSubscribed {

        notifiers.remove(notifier);
    }
}
