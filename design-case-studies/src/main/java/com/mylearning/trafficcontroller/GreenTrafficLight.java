package com.mylearning.trafficcontroller;

public class GreenTrafficLight extends TrafficLight {
    TrafficSignal trafficSignal;

    public GreenTrafficLight(TrafficSignal trafficSignal, int time) {
        super(time);
        this.trafficSignal = trafficSignal;
    }

    public void update(){
        try {
            Thread.sleep(super.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Turning traffic light to yellow!!");
        trafficSignal.setCurrentState(trafficSignal.getYellowTrafficLight());
    }

    public String toString() {
        return "Traffic Signal is green!!";
    }
}
