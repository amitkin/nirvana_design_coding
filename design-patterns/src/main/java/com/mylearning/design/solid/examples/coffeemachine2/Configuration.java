package com.mylearning.design.solid.examples.coffeemachine2;

public class Configuration {

	private double quantityCoffee;
	private double quantityWater;

	public Configuration(double quantityCoffee, double quantityWater) {
		this.quantityCoffee = quantityCoffee;
		this.quantityWater = quantityWater;
	}

	public double getQuantityCoffee() {
		return quantityCoffee;
	}

	public double getQuantityWater() {
		return quantityWater;
	}

}
