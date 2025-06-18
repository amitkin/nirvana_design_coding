package com.mylearning.design.patterns.pluralsight.behavioral.observer;

public abstract class Observer {

    protected Subject subject;

    //If update is empty then Subject or Observable needs to be there in Observer
    //Or pass observer in the update itself
    abstract void update();
}
