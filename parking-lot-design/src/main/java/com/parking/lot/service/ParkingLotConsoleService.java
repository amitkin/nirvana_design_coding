package com.parking.lot.service;

import com.parking.lot.entities.BMW;
import com.parking.lot.entities.ParkingSpace;
import com.parking.lot.entities.SmallParkingSpace;
import com.parking.lot.exception.ParkingException;

public class ParkingLotConsoleService {
    public static void main(String[] args) {
        BMW bmw = new BMW();
        ParkingSpace parkingSpace = new SmallParkingSpace();
        try {
            parkingSpace.park(bmw);
        } catch (ParkingException e) {
            e.printStackTrace();
        }
    }
}
