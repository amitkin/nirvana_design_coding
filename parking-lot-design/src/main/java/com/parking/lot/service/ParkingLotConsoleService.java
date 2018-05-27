package com.parking.lot.service;

import com.parking.lot.entities.BMW;
import com.parking.lot.entities.ParkingLot;
import com.parking.lot.entities.WeekdayAlgorithm;

public class ParkingLotConsoleService {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getParkingLot(new WeekdayAlgorithm());

        BMW bmw = new BMW();
        parkingLot.parkVehicle(bmw);
        parkingLot.removeVehicle(bmw);
    }
}
