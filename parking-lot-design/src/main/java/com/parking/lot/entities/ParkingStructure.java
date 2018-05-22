package com.parking.lot.entities;

import java.util.Hashtable;
import java.util.List;

public class ParkingStructure{
    List<ParkingSpace> availableSpaces;

    //Key her will be LicensePlate
    Hashtable<String, ParkingSpace> usedSpaces;

    PaymentAlgorithm paymentAlgorithm;

    private static ParkingStructure parkingStructure = null;

    private ParkingStructure(PaymentAlgorithm paymentAlgorithm){
        this.paymentAlgorithm = paymentAlgorithm;
    }

    public static ParkingStructure getParkingStructure(PaymentAlgorithm paymentAlgorithm){
        if(parkingStructure == null){
            parkingStructure = new ParkingStructure(paymentAlgorithm);
        }
        return parkingStructure;
    }

    public int calculatePayment(ParkingSpace parkingSpace, ParkingSlip parkingSlip){
        return paymentAlgorithm.calculatePayment(parkingSpace, parkingSlip);
    }

    //Imp : Finding free spot in optimized manner
    public ParkingSpace getParkingSpace(Vehicle v) {
        //Based on the size of Vehicle return the Parking space
        //In order to find the parking space in optimized manner list is not good since it will be linear operation
        //Stacks can be used to store S, M, L, XL size parking spaces so search will be O(1), worst case 4 lookups
        //So if there is truck(size L) search in the L and XL stack
        //Once we get the space put it in the HashTable with LicensePlate as key

        ParkingSpace space = null;
        if(v.getSize() == Size.S){
            space = new SmallParkingSpace();
            usedSpaces.put(v.getLicensePlate(), space);
        }
        return space;
    }

    public ParkingSpace freeParkingSpace(Vehicle v) {
        return usedSpaces.remove(v.getLicensePlate());
    }

    public ParkingSpace findMyParkingSpace(Vehicle v) {
        return usedSpaces.get(v.getLicensePlate());
    }
}
