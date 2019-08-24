package com.mylearning.trafficcontroller;

public class RedTrafficLight extends TrafficLight {
    private TrafficSignal trafficSignal;

    public RedTrafficLight(TrafficSignal trafficSignal) {
        this.trafficSignal = trafficSignal;
    }

    public void handleRequest(){
        System.out.println("Turning traffic light to green!!");
        trafficSignal.setCurrentState(trafficSignal.getGreenTrafficLight());
    }

    public String toString() {
        return "Traffic Signal is green!!";
    }
}
