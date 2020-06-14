package com.mylearning.trafficcontroller;

/*
The car dimensions are such that each car fits inside two road places. The car object will have two links to the two places
it occupies on the road. The car object will also record the direction in which it is facing. This information provides
the information needed to display a car or to repaint the background as the car moves.

Member functions will allow us to check if a car can move, to move the car, to check if it is at the end of the road, and,
of course, to erase and display the car. To do this it needs access to the two Place objects that represent its position. These
cannot be member data of the Car object - we need to use only reference to the Place objects that are part of the Road.

Car can also collect statistics about itself - the total time on the road, the waiting time, the distance traveled, etc.
We see that a car designed this way has no knowledge of the world around it other than its position on the road, the
direction of travel, and whether the place ahead is free.

The Road class is a classic example of a static linked structure that is traversed or visited by other objects, but
its structure does not change throughout the duration of the simulation.
*/
public class Car {

    public boolean canMove() {
        return true;
    }

    public void move() {
        //uses next() function in Place to move
        //As the car moves, it unblocks the Place in the rear and blocks the Place ahead
    }

    public boolean isAtEnd() {
        return true;
    }
}
