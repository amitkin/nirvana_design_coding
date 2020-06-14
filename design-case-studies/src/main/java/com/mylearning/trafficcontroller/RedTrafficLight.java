package com.mylearning.trafficcontroller;

public class RedTrafficLight extends TrafficLight {
    private TrafficSignal trafficSignal;

    public RedTrafficLight(TrafficSignal trafficSignal, int time) {
        super(time);
        this.trafficSignal = trafficSignal;
    }

    public void update(){
        try {
            Thread.sleep(super.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Turning traffic light to green!!");
        trafficSignal.setCurrentState(trafficSignal.getGreenTrafficLight());
    }

    public String toString() {
        return "Traffic Signal is green!!";
    }
}
