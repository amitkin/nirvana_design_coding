package com.mylearning.design.patterns.pluralsight.behavioral.visitor.bad;

public class VisitorDemo {

    public static void main(String[] args) {
        PartsOrder order = new PartsOrder();
        order.addPart(new Wheel());
        order.addPart(new Fender());
        order.addPart(new Oil());

        System.out.println("Shipping: " + order.calculateShipping());
    }
}
