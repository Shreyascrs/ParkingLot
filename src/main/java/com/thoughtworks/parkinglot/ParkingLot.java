package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot implements IParkingLotObserver {

    private int capacity;
    private Person owner;

    List<Person> notifiers;
    Set<Object> vehicles = new HashSet<>();

    public ParkingLot(int capacity, Person owner) {
        this.capacity = capacity;
        this.owner = owner;
        notifiers = new ArrayList<>();
    }


    public void park(Object object) throws ParkingLotFullException, VehicleAlreadyParkedException {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException("parking Lot is Full");
        }

        if (isParked(object)) {
            throw new VehicleAlreadyParkedException("vehicle already parked");
        }
        vehicles.add(object);
        if (isFull(capacity)) {
            owner.notifyWhenFull();
            for (Person p : notifiers) {
                p.notifyWhenFull();
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
            owner.notifyWhenEmpty();
            for (Person p : notifiers) {
                p.notifyWhenEmpty();
            }
            return vehicle;
        }
        vehicles.remove(vehicle);
        return vehicle;
    }

    private boolean isPresent(Object vehicle) {
        return !(vehicles.contains(vehicle));
    }

    private boolean isEmpty() {
        return vehicles.size() == 0;
    }

    private boolean isParked(Object object) {
        return vehicles.contains(object);
    }

    protected boolean isParkingLotFull() {
        return vehicles.size() >= capacity;
    }

    @Override
    public void register(Object object) {
        notifiers.add((Person) object);
    }
}
