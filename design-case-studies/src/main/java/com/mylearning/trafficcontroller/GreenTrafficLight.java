package com.mylearning.trafficcontroller;

public class GreenTrafficLight extends TrafficLight {
    TrafficSignal trafficSignal;

    public GreenTrafficLight(TrafficSignal trafficSignal) {
        this.trafficSignal = trafficSignal;
    }

    public void handleRequest(){
        System.out.println("Turning traffic light to yellow!!");
        trafficSignal.setCurrentState(trafficSignal.getYellowTrafficLight());
    }

    public String toString() {
        return "Traffic Signal is green!!";
    }
}
