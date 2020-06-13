package com.mylearning.design.patterns.builder;

public class MovieTicketBooking {

    private final String movie;     // Name of the Movie
    private final int totalTickets; // Total tickets booked
    private final int popcorn;      // quantity of popcorn
    private final int pizza;        // quantity of Pizza
    private final int burger;       // quantity of burger
    private final int coke;         // quantity of coke

    private MovieTicketBooking(Builder builder)
    {
        movie = builder.movie;
        totalTickets = builder.totalTickets;
        popcorn = builder.popcorn;
        pizza = builder.pizza;
        burger = builder.burger;
        coke = builder.coke;
    }

    public static class Builder{
        private final String movie;
        private final int totalTickets;
        private int popcorn;
        private int pizza;
        private int burger;
        private int coke;

        public Builder(String movie, int totalTickets) {
            this.movie = movie;
            this.totalTickets = totalTickets;
        }

        public Builder popcorn(int val){
            popcorn = val;
            return this;
        }

        public Builder pizza(int val){
            pizza = val;
            return this;
        }

        public Builder burger(int val){
            burger = val;
            return this;
        }

        public Builder coke(int val){
            coke = val;
            return this;
        }

        public MovieTicketBooking build()
        {
            return new MovieTicketBooking(this);
        }
    }
}
