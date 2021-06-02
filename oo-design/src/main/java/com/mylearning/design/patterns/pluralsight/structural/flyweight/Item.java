package com.mylearning.design.patterns.pluralsight.structural.flyweight;

// Instances of this item will be the flyweights
public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
