package com.parking.lot.entities;

import com.parking.lot.exception.ParkingException;
import com.parking.lot.exception.PaymentException;

public abstract class ParkingSpace {
    boolean isVacant;
    VehicleSize vehicleSize;
    Vehicle vehicle;

    private ParkingLot parkingLot;

    public ParkingSlip park(Vehicle v) throws ParkingException {
        this.vehicle = v;
        this.isVacant = false;
        return null;
    }

    public boolean unpark(ParkingSlip parkingSlip) throws ParkingException {
        //At the time of exit calculatePayment using the entry time in Parking Slip
        parkingLot.calculatePayment(this, parkingSlip);
        return true;
    }

    public int getParkingFees(){
        return 0;
    }

    public void payParkingFess(int payment) throws PaymentException {

    }

    public abstract int getHourlyRate();
}
