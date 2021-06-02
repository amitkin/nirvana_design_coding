package com.mylearning.design.patterns.pluralsight.behavioral.visitor.bad;

public class Fender implements AtvPart {

    @Override
    public double calculateShipping() {
        return 3.00;
    }
}
