package com.parking.lot.entities;

public enum VehicleType {
    BIKE(100.0),
    BICYCLE(90.0),
    TRUCK(1000.0),
    CAR(400.0);

    private double maxHeight;

    VehicleType(double maxHeight) {
        this.maxHeight = maxHeight;
    }
}
