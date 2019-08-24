package com.mylearning.trafficcontroller;

public class TrafficSignal {

    private RedTrafficLight redTrafficLight;
    private GreenTrafficLight greenTrafficLight;
    private YellowTrafficLight yellowTrafficLight;

    private TrafficLight currentState;

    public TrafficSignal() {
        redTrafficLight = new RedTrafficLight(this);
        greenTrafficLight = new GreenTrafficLight(this);
        yellowTrafficLight = new YellowTrafficLight(this);
        this.currentState = redTrafficLight;
    }

    public void init(){
        currentState.handleRequest();
    }

    public RedTrafficLight getRedTrafficLight() {
        return redTrafficLight;
    }

    public GreenTrafficLight getGreenTrafficLight() {
        return greenTrafficLight;
    }

    public YellowTrafficLight getYellowTrafficLight() {
        return yellowTrafficLight;
    }

    public void setCurrentState(TrafficLight currentState) {
        this.currentState = currentState;
    }
}
