package com.mylearning.design.solid.examples.coffeemachine2;

public interface CoffeeMachine {
	
	CoffeeDrink brewCoffee(CoffeeSelection selection) throws CoffeeException;
}