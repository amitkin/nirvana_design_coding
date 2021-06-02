package com.mylearning.design.patterns.pluralsight.behavioral.command;

//invoker
public class Switch {

	public void storeAndExecute(Command command) {
		command.execute();
	}
}
