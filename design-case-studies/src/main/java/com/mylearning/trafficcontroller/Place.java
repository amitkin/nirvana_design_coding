package com.mylearning.trafficcontroller;

/*
This class is at the heart of the entire program. Each place represents one patch of asphalt on the road.
It contains four pointers to other places adjacent to this one. The pointers contain valid links only if travel in that
direction is permitted. So, all places in the northbound lane (except for the last one) will have links to their neighbors
north of them, but no other links. When we start building intersections, the places where two directions of travel cross
will have links to both neighbors. A place can be blocked if it is occupied by a car or if the light ahead is not green.

There should be no member function in the Place class that changes places links. The constructor in the Place class should set these
links to null.
*/
public class Place {
    private Place north, south, east, west;

    public Place() {
        this.north = null;
        this.south = null;
        this.east = null;
        this.west = null;
    }

    //Indicates whether there is a link to another Place object in that direction and whether this object is currently marked as free.
    public boolean freeToMove(Direction direction) {
        return true;
    }

    //Returns a pointer to the next Place object. It is used by the car's move() function.
    public void next() {

    }
}
