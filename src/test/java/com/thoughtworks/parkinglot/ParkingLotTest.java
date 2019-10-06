package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DummyOwner implements INotification {

    int messageWhenFull;
    int messageWhenEmpty;

    @Override
    public void notifyWhenFull() {
        messageWhenFull++;
    }

    @Override
    public void notifyWhenEmpty() {
        messageWhenEmpty++;
    }
}

class SecurityGuard implements INotification {

    int messageWhenFull;
    int messageWhenEmpty;

    @Override
    public void notifyWhenFull() {
        messageWhenFull++;
    }

    @Override
    public void notifyWhenEmpty() {
        messageWhenEmpty++;
    }
}

class ParkingLotTest {

    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isParkingLotFull());
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);

        parkingLot.park(new Object());

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, notifierlist);

        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void givenParkingLotWithOneVehicleAndGivenOtherVehicleToUnpark_WhenUnPark_ThenMustNotUnPark() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertThrows(VehicleNotFoundException.class, () -> parkingLot.unPark(new Object()));
    }

    @Test
    void givenParkingLotWithOneVehicle_WhenUnPark_ThenMustUnPark() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithOneVehicleMustUnparkAndOnceAgainUnpark_WhenUnPark_ThenMustThrowException() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        assertThrows(VehicleNotFoundException.class, () -> parkingLot.unPark(vehicle));
    }


    @Test
    void givenParkingLotWithTwoVehicles_WhenUnpark_ThenUnpark() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(3, notifierlist);
        Object vehicle1 = new Object();
        Object vehicle2 = new Object();

        parkingLot.park(vehicle1);
        parkingLot.park(vehicle2);

        assertEquals(vehicle1, parkingLot.unPark(vehicle1));
        assertEquals(vehicle2, parkingLot.unPark(vehicle2));

    }

    @Test
    void givenParkingLotfull_WhenPark_ThenOwnerMustGetMessage() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);

        parkingLot.park(new Object());

    }

    @Test
    void givenParkingLotfull_WhenPark_ThenhowManyTimesMethodCalled() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);

        parkingLot.park(new Object());
        assertEquals(1, owner.messageWhenFull);

    }

    @Test
    void givenParkingLotFullParkedAndUnParked_WhenPark_ThenhowManyTimesMethodCalled() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        parkingLot.park(vehicle);

        assertEquals(2, owner.messageWhenFull);
        assertEquals(1, owner.messageWhenEmpty);

    }

    @Test
    void givenWhenTheParkingLotIsFullWhenVehicleRemoved_WhenUnPark_ThenSendNotification() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

    }

    @Test
    void givenWhenTheParkingLotIsFullWhenVehicleremoved_WhenunPark_ThenSendNotification() throws Exception {
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(4, notifierlist);


        Object vehicle1 = new Object();
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();

        //adding vehicle
        parkingLot.park(vehicle1);
        parkingLot.park(vehicle2);
        parkingLot.park(vehicle3);
        parkingLot.park(vehicle4);  //must recive notification count=1

        assertEquals(1, owner.messageWhenFull);

        //removeing vehicle
        parkingLot.unPark(vehicle1); //must recive a notification count=2
        parkingLot.unPark(vehicle2); //no notification will trigger to owner

        assertEquals(1, owner.messageWhenEmpty);
    }

    @Test
    void givenWhenTheParkingLotIsFullWhenVehicleremoved_WhenUnPark_ThenSendNotification() throws Exception {
        SecurityGuard securityGuard = new SecurityGuard();
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        notifierlist.add(securityGuard);
        ParkingLot parkingLot = new ParkingLot(2, notifierlist);

        Object firstVehicle = new Object();
        Object secondVehicle = new Object();

        //adding vehicle
        parkingLot.park(firstVehicle);
        parkingLot.park(secondVehicle); //must recive notification count=1

        assertEquals(1, owner.messageWhenFull);
        assertEquals(1, securityGuard.messageWhenFull);

        //removing vehicle
        parkingLot.unPark(firstVehicle); //must recive a notification count=2
        parkingLot.unPark(secondVehicle); //no notification will trigger to owner

        assertEquals(1, owner.messageWhenEmpty);
        assertEquals(1, securityGuard.messageWhenEmpty);
    }

    @Test
    void givenParkingLot_WhenPersonSubscribes_ThenGetNotification() throws Exception {
        SecurityGuard securityGuard = new SecurityGuard();
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        Object firstVehicle = new Object();

        parkingLot.park(firstVehicle);
        assertEquals(1, owner.messageWhenFull);

        parkingLot.subscribe(securityGuard);
        parkingLot.unPark(firstVehicle);
        assertEquals(1, owner.messageWhenEmpty);
        assertEquals(1, securityGuard.messageWhenEmpty);

    }

    @Test
    void givenParkingLot_WhenPersonUnSubscribes_ThenMustNotGetNotification() throws Exception {
        SecurityGuard securityGuard = new SecurityGuard();
        DummyOwner owner = new DummyOwner();
        List<INotification> notifierlist = new ArrayList<>();
        notifierlist.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        Object firstVehicle = new Object();

        parkingLot.park(firstVehicle);
        assertEquals(1, owner.messageWhenFull);

        parkingLot.subscribe(securityGuard);
        parkingLot.unPark(firstVehicle);
        assertEquals(1, owner.messageWhenEmpty);
        assertEquals(1, securityGuard.messageWhenEmpty);

        parkingLot.unSubscribe(securityGuard);
        parkingLot.park(firstVehicle);
        assertEquals(0, securityGuard.messageWhenFull);

    }

    @Test
    void givenAnParkingLotWithOneCapacity_WhenGetCapacity_ThenProvideCapacity() {
        List<INotification> notifierlist = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        assertEquals(1, parkingLot.getCapacity());
    }

    @Test
    void givenAnParkingLotWithOneCapacity_WhenGetAvailableSpace_ThenProvideAvailableSpace() {
        List<INotification> notifierlist = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1, notifierlist);
        assertEquals(1, parkingLot.getAvailableSpace());
    }
}
