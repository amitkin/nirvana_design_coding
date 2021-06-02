package com.mylearning.design.patterns.pluralsight.behavioral.visitor;

import java.util.List;

public class AtvPartsShippingVisitor implements AtvPartVisitor {

    double shippingAmount = 0;

    @Override
    public void visit(Wheel wheel) {
        double amount = 15.00;
        System.out.println("Wheels are bulky and expensive to ship: " + amount);
        shippingAmount += amount;
    }

    @Override
    public void visit(Fender fender) {
        double amount = 3.00;
        System.out.println("Fenders are light and cheap: " + amount);
        shippingAmount += amount;
    }

    @Override
    public void visit(Oil oil) {
        double amount = 9.00;
        System.out.println("Oil is hazardous: " + amount);
        shippingAmount += amount;
    }

    @Override
    public void visit(PartsOrder partsOrder) {
        System.out.println("If they bought more than 2 things, we give them a 20% discount on shipping");
        List<AtvPart> parts = partsOrder.getParts();
        if (parts.size() > 2) {
            shippingAmount *= .8;
        }

        System.out.println("Final shipping calculation is: " + shippingAmount);
    }
}
