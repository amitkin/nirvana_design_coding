package com.parking.lot.entities;

import com.parking.lot.exception.ParkingException;
import com.parking.lot.exception.PaymentException;

public abstract class ParkingSpace {
    private Vehicle vehicle;
    private ParkingStructure parkingStructure;

    public ParkingSlip park(Vehicle v) throws ParkingException {
        return null;
    }

    public boolean unpark(ParkingSlip parkingSlip) throws ParkingException {
        //At the time of exit calculatePayment using the entry time in Parking Slip
        parkingStructure.calculatePayment(this, parkingSlip);
        return true;
    }

    public int getParkingFees(){
        return 0;
    }

    public void payParkingFess(int payment) throws PaymentException {

    }

    public abstract int getHourlyRate();
}
