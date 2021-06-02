package com.mylearning.ticketbooking.providers;

import com.mylearning.ticketbooking.model.Seat;
import com.mylearning.ticketbooking.model.Show;

import java.util.List;

public interface SeatLockProvider {

    void lockSeats(Show show, List<Seat> seat, String user);
    void unlockSeats(Show show, List<Seat> seat, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
