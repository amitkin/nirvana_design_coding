package com.mylearning.design.patterns.pluralsight.behavioral.strategy;

public class StrategyDemo {

    public static void main(String[] args) {
        CreditCard amexCard1 = new CreditCard(new AmexStrategy());
        amexCard1.setNumber("379185883464283");
        amexCard1.setDate("04/2020");
        amexCard1.setCvv("123");
        System.out.println("Is Amex1 valid: " + amexCard1.isValid());

        CreditCard amexCard2 = new CreditCard(new AmexStrategy());
        amexCard2.setNumber("379185883464282");
        amexCard2.setDate("04/2020");
        amexCard2.setCvv("987");
        System.out.println("Is Amex2 valid: " + amexCard2.isValid());

        CreditCard visaCard1 = new CreditCard(new VisaStrategy());
        visaCard1.setNumber("4539589763663402");
        visaCard1.setDate("05/2018");
        visaCard1.setCvv("342");
        System.out.println("Is Visa1 valid: " + visaCard1.isValid());

    }
}
