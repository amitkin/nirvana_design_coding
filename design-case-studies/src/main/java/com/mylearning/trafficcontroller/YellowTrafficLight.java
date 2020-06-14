package com.mylearning.trafficcontroller;

public class YellowTrafficLight extends TrafficLight {
    TrafficSignal trafficSignal;

    public YellowTrafficLight(TrafficSignal trafficSignal, int time) {
        super(time);
        this.trafficSignal = trafficSignal;
    }

    public void update() {
        try {
            Thread.sleep(super.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Turning traffic light to red!!");
        trafficSignal.setCurrentState(trafficSignal.getRedTrafficLight());
    }

    public String toString() {
        return "Traffic Signal is green!!";
    }
}
