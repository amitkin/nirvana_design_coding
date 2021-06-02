package com.mylearning.design.patterns.pluralsight.behavioral.strategy;

public class VisaStrategy extends CreditCardValidationStrategy {

    @Override
    public boolean isValid(CreditCard creditCard) {
        return creditCard.getNumber().startsWith("4")
                && creditCard.getNumber().length() == 16
                && passesLuhn(creditCard.getNumber());
    }
}
