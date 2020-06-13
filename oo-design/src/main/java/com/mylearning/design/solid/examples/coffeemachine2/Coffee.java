package com.mylearning.design.solid.examples.coffeemachine2;

public class Coffee {

	private String name;

	private double quantity;

	public Coffee(String name, double quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
