package com.mylearning.design.patterns.visitorPattern;

public class Body implements Visitable {

	public void accept(Visitor visitor) {
		visitor.visit(this);

	}

}
