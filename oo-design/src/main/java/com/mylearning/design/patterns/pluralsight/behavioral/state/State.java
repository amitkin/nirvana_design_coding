package com.mylearning.design.patterns.pluralsight.behavioral.state;

public abstract class State {
	
	public void handleRequest() {
		System.out.println("Shouldn't be able to get here.");
	}
}
