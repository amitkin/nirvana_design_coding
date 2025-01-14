package com.mylearning.design.patterns.visitorPattern;

public interface Visitor {
	void visit(Wheel wheel);
	void visit(Engine engine);
	void visit(Body body);
	void visit(Car car);
}
