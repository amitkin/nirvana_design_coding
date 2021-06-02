package com.mylearning.design.patterns.pluralsight.behavioral.strategy;

public class AmexStrategy extends CreditCardValidationStrategy {

    @Override
    public boolean isValid(CreditCard creditCard) {
        return (creditCard.getNumber().startsWith("34") || creditCard.getNumber().startsWith("37"))
                && creditCard.getNumber().length() == 15
                && passesLuhn(creditCard.getNumber());
    }
}
