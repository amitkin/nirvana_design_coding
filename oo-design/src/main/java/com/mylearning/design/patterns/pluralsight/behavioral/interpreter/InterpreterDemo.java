package com.mylearning.design.patterns.pluralsight.behavioral.interpreter;

public class InterpreterDemo {

    static Expression buildInterpreterTree() {
        Expression e1 = new TerminalExpression("Lions");
        Expression e2 = new TerminalExpression("Tigers");
        Expression e3 = new TerminalExpression("Bears");
        Expression e4 = new AndExpression(e2, e3);
        Expression e5 = new OrExpression(e1, e4);

        return new AndExpression(e1, e5);
    }

    public static void main(String[] args) {
//        String context = "Lions";
//        String context = "Tigers";
//        String context = "Bears";
//        String context = "Lions Tigers";
        String context = "Lions Bears";
//        String context = "Tigers Bears";

        Expression define = buildInterpreterTree();

        System.out.println(context + " is " + define.interpret(context));
    }
}
