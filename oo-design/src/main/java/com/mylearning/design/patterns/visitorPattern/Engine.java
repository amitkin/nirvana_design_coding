package com.mylearning.design.patterns.visitorPattern;

public class Engine implements Visitable {

	public void accept(Visitor visitor) {
		visitor.visit(this);

	}

}
