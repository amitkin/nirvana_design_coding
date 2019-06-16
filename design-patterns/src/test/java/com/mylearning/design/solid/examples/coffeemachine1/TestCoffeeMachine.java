package com.mylearning.design.solid.examples.coffeemachine1;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestCoffeeMachine {

	@Test
	public void basicCoffeeMachineAsCoffeeMachine() {
		// create a Map of available coffee beans
		Map<CoffeeSelection, GroundCoffee> beans = new HashMap<CoffeeSelection, GroundCoffee>();
		beans.put(CoffeeSelection.FILTER_COFFEE, new GroundCoffee(
				"My favorite filter coffee bean", 1000));

		// instantiate a new CoffeeMachine object
		CoffeeMachine coffeeMachine = new BasicCoffeeMachine(beans);

		CoffeeDrink coffee = coffeeMachine.brewFilterCoffee();
	}

	@Test
	public void premiumCoffeeMachineAsCoffeeMachine() throws CoffeeException {
		// create a Map of available coffee beans
		Map<CoffeeSelection, CoffeeBean> beans = new HashMap<CoffeeSelection, CoffeeBean>();
		beans.put(CoffeeSelection.FILTER_COFFEE, new CoffeeBean(
				"My favorite filter coffee bean", 1000));
		beans.put(CoffeeSelection.ESPRESSO, new CoffeeBean(
				"My favorite espresso bean", 1000));

		// instantiate a new CoffeeMachine object
		EspressoMachine coffeeMachine = new PremiumCoffeeMachine(beans);

		CoffeeDrink coffee = coffeeMachine.brewEspresso();
	}
}
