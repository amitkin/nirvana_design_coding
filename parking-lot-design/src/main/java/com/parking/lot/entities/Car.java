package com.parking.lot.entities;

public class Car implements Vehicle{

    @Override
    public String getLicensePlate() {
        return null;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.M;
    }
}
