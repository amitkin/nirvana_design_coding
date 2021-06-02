package com.mylearning.design.patterns.pluralsight.structural.flyweight;

public class FlyweightInventoryDemo {

    public static void main(String[] args) {
        InventorySystem is = new InventorySystem();

        is.takeOrder("Roomba", 221);
        is.takeOrder("Bose Headphones", 432);
        is.takeOrder("Samsung TV", 324);
        is.takeOrder("Samsung TV", 345);
        is.takeOrder("Roomba", 745);
        is.takeOrder("Bose Headphones", 153);
        is.takeOrder("Roomba", 112);
        is.takeOrder("Samsung TV", 869);
        is.takeOrder("Roomba", 675);
        is.takeOrder("Bose Headphones", 747);
        is.takeOrder("Samsung TV", 779);
        is.takeOrder("Roomba", 540);

        is.process();

        is.report();
    }
}
