package com.mylearning.design.builder;

public class BuilderDesignPattern {
    public static void main(String[] args) {
        MovieTicketBooking movieTicketBooking = new MovieTicketBooking.Builder("3 Idiots", 5)
                .popcorn(2)
                .burger(3)
                .coke(5)
                .build();
    }
}
