package com.mylearning.design.patterns.pluralsight.behavioral.strategy;

public class CreditCard {

    private String number;
    private String date;
    private String cvv;

    private CreditCardValidationStrategy validationStrategy;

    public CreditCard(CreditCardValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public CreditCard(String number, String date, String cvv) {
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public boolean isValid() {
        return validationStrategy.isValid(this);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
