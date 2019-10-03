package com.thoughtworks.parkinglot.consumer;

import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

public class Sanjay {

    public static void park(ParkingLot one, ParkingLot two) {

        Object firstCar = new Object();
        Object secondCar = new Object();
        Object thirdCar = new Object();
        Object fourthCar = new Object();

        Object[] vehicals = {firstCar, firstCar, secondCar, thirdCar, firstCar, fourthCar};

        for (int i = 0; i < vehicals.length; i++) {
            try {
                one.park(vehicals[i]);
                System.out.println("vehicle added in parking lot one " + vehicals[i] + "\n");
            } catch (ParkingLotFullException e) {
                System.out.println("parking lot one is full " + vehicals[i] + "\n");
                try {
                    two.park(vehicals[i]);
                    System.out.println("vehicle added in parking lot two " + vehicals[i] + "\n");
                } catch (ParkingLotFullException ex) {
                    System.out.println("parking lot two is full " + vehicals[i] + "\n");
                } catch (VehicleAlreadyParkedException ex) {
                    System.out.println("vehicle already exist in parking lot one " + vehicals[i] + "\n");
                }
            } catch (VehicleAlreadyParkedException e) {
                System.out.println("vehicle already exist in parking lot one " + vehicals[i] + "\n");
            }
        }

    }

    public static void main(String[] args) throws ParkingLotFullException {
        ParkingLot one = new ParkingLot(2);
        ParkingLot two = new ParkingLot(3);

        Sanjay.park(one, two);
        //Sanjay2.park(one,two);
    }
}
