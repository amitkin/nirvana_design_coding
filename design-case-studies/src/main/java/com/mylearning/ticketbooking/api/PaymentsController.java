package com.mylearning.ticketbooking.api;

import com.mylearning.ticketbooking.services.BookingService;
import com.mylearning.ticketbooking.services.PaymentsService;
import lombok.NonNull;

public class PaymentsController {
    private final PaymentsService paymentsService;
    private final BookingService bookingService;

    public PaymentsController(PaymentsService paymentsService, BookingService bookingService) {
        this.paymentsService = paymentsService;
        this.bookingService = bookingService;
    }

    public void paymentFailed(@NonNull final String bookingId, @NonNull final String user) {
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }

    public void paymentSuccess(@NonNull final  String bookingId, @NonNull final String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }

}
