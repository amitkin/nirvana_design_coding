package com.mylearning.design.patterns.pluralsight.behavioral.visitor.bad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartsOrder implements AtvPart {

    private List<AtvPart> parts = new ArrayList<>();

    public PartsOrder() {
    }

    public double calculateShipping() {
        double shippingCost = 0;
        for(AtvPart part : parts) {
            shippingCost += part.calculateShipping();
        }

        return shippingCost;
    }

    public void addPart(AtvPart part) {
        parts.add(part);
    }

    public List<AtvPart> getParts() {
        return Collections.unmodifiableList(parts);
    }

}
