package com.mylearning.design.patterns.pluralsight.behavioral.template;

public class TemplateDemo {

    public static void main(String[] args) {
        System.out.println("Web Order:");

        OrderTemplate webOrder = new WebOrder();
        webOrder.processOrder();

        System.out.println("\n\nStore Order:");

        OrderTemplate storeOrder = new StoreOrder();
        storeOrder.processOrder();

    }
}
