package com.parking.lot.allocator;

import com.parking.lot.entities.ParkingSpace;
import com.parking.lot.entities.Vehicle;
import com.parking.lot.exception.SpotNotAvailableException;

public interface ParkingSpotAllocator {
    public ParkingSpace assign(Vehicle vehicle) throws SpotNotAvailableException;
}
