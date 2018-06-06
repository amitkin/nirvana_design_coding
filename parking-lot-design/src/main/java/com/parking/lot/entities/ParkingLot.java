package com.parking.lot.entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.parking.lot.exception.ParkingException;

public class ParkingLot {
    List<ParkingSpace> vacantParkingSpaces = new ArrayList<>();
    List<ParkingSpace> fullParkingSpaces = new ArrayList<>();

    int parkingSpaceCount = 0;

    private boolean isFull;
    private boolean isEmpty;

    //Key will be LicensePlate
    Hashtable<String, ParkingSpace> usedSpaces = new Hashtable<>();

    PaymentAlgorithm paymentAlgorithm;

    private static ParkingLot parkingLot = null;

    private ParkingLot(PaymentAlgorithm paymentAlgorithm){
        this.paymentAlgorithm = paymentAlgorithm;
    }

    public static ParkingLot getParkingLot(PaymentAlgorithm paymentAlgorithm){
        if(parkingLot == null){
            parkingLot = new ParkingLot(paymentAlgorithm);
        }
        return parkingLot;
    }

    public int calculatePayment(ParkingSpace parkingSpace, ParkingSlip parkingSlip){
        return paymentAlgorithm.calculatePayment(parkingSpace, parkingSlip);
    }

    private ParkingSpace findNearestVacant(VehicleSize vehicleSize)
    {
        Iterator<ParkingSpace> itr = vacantParkingSpaces.iterator();

        while(itr.hasNext())
        {
            ParkingSpace parkingSpace = itr.next();

            if(parkingSpace.vehicleSize == vehicleSize)
            {
                return parkingSpace;
            }
        }
        return null;
    }

    //Imp : Finding free spot in optimized manner
    public void parkVehicle(Vehicle vehicle) {
        //Based on the size of Vehicle return the Parking space
        //In order to find the parking space in optimized manner list is not good since it will be linear operation
        //Stacks can be used to store S, M, L, XL size parking spaces so search will be O(1), worst case 4 lookups
        //So if there is truck(size L) search in the L and XL stackqueue
        //Once we get the space put it in the HashTable with LicensePlate as key

        if(!isFull())
        {
            ParkingSpace parkingSpace = findNearestVacant(vehicle.getSize());

            if(parkingSpace != null)
            {
                try {
                    parkingSpace.park(vehicle);
                    usedSpaces.put(vehicle.getLicensePlate(), parkingSpace);
                } catch (ParkingException e) {
                    e.printStackTrace();
                }

                vacantParkingSpaces.remove(parkingSpace);
                fullParkingSpaces.add(parkingSpace);

                if(fullParkingSpaces.size() == parkingSpaceCount)
                    isFull = true;

                isEmpty = false;
            }
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        if(!isEmpty())
        {
            Iterator<ParkingSpace> itr = fullParkingSpaces.iterator();

            while(itr.hasNext())
            {
                ParkingSpace parkingSpace = itr.next();

                if(parkingSpace.vehicle.getLicensePlate().equals(vehicle.getLicensePlate()))
                {
                    fullParkingSpaces.remove(parkingSpace);
                    vacantParkingSpaces.add(parkingSpace);
                    usedSpaces.remove(vehicle.getLicensePlate());

                    parkingSpace.isVacant = true;
                    parkingSpace.vehicle = null;

                    if(vacantParkingSpaces.size() == parkingSpaceCount)
                        isEmpty = true;

                    isFull = false;
                }
            }
        }
    }

    public ParkingSpace findMyParkingSpace(Vehicle v) {
        return usedSpaces.get(v.getLicensePlate());
    }

    public boolean isFull()
    {
        return isFull;
    }

    public boolean isEmpty()
    {
        return isEmpty;
    }
}
