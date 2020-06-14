package com.mylearning.trafficcontroller;

import java.util.LinkedList;
import java.util.List;

/*
Each lane of traffic is represented by one object in this class. The Road object knows its direction, can return a
pointer to a specified place on the road, especially to the first place where a car will enter the road.
*/
public class Road {

    //Each Road object is a linked list of Place objects
    private List<Place> places = new LinkedList<>();

    //Each road has only one signal
    private TrafficSignal trafficSignal;

    public Road(int greentime, int redtime, int yellowtime) {
        this.trafficSignal = new TrafficSignal(greentime, redtime, yellowtime);
        populatePlaces();
    }

    public void startSignal() {
        this.trafficSignal.change();
    }

    public void populatePlaces() {
        //Road class populates the Place objects and initializes links between them to represent roadways. Places
        //that are at the intersection of two roadways are created by the first Road object and the intersecting
        //Road object has to reference the already existing Place object.

    }

    public List<Place> getPlaces() {
        return places;
    }
}
