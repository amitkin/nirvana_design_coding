package com.mylearning.trafficcontroller;

public class YellowTrafficLight extends TrafficLight {
    TrafficSignal trafficSignal;

    public YellowTrafficLight(TrafficSignal trafficSignal) {
        this.trafficSignal = trafficSignal;
    }

    public void handleRequest(){
        System.out.println("Turning traffic light to red!!");
        trafficSignal.setCurrentState(trafficSignal.getRedTrafficLight());
    }

    public String toString() {
        return "Traffic Signal is green!!";
    }
}
