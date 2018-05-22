package com.parking.lot.entities;

public class Car implements Vehicle{

    @Override
    public String getLicensePlate() {
        return null;
    }

    @Override
    public VehicleType getVehicleType() {
        return null;
    }

    @Override
    public Size getSize() {
        return Size.M;
    }
}
