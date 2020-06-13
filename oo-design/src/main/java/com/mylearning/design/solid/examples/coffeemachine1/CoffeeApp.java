package com.mylearning.design.solid.examples.coffeemachine1;

public class CoffeeApp {
	
	private CoffeeMachine coffeeMachine;
	
	public CoffeeApp(CoffeeMachine coffeeMachine) {
		this.coffeeMachine = coffeeMachine;
	}
	
	public CoffeeDrink prepareCoffee() throws CoffeeException {
		CoffeeDrink coffee = coffeeMachine.brewFilterCoffee();
		System.out.println("Coffee is ready!");
		return coffee;
	}
} // end CoffeeApp
