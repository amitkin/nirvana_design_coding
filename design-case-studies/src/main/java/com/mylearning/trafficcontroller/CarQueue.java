package com.mylearning.trafficcontroller;

import java.util.LinkedList;
import java.util.Queue;

/*
There is one CarQueue object for each direction of travel.
*/
public class CarQueue {
    Queue<Car> carQueue = new LinkedList<>();

    public void enter(Car car) {
        //New cars enter at the tail
        carQueue.add(car);
    }

    public void remove() {
        //cars that reach the end are removed from the head
        Car car = carQueue.peek();
        if(car.isAtEnd()) {
            carQueue.remove();
        }
    }

}
