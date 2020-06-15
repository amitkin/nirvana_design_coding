package com.mylearning.trafficcontroller;

/*
All the TrafficSignal will have different timer for switching lights(which is G->Y->R->G).
So constructor of TrafficSignal should accept three parameters for light’s timing. So that,
it should be like TrafficSignal(int greentime, int redtime, int yellowtime).
*/
public class TrafficSignal {

    private RedTrafficLight redTrafficLight;
    private GreenTrafficLight greenTrafficLight;
    private YellowTrafficLight yellowTrafficLight;

    private TrafficLight currentState;

    public TrafficSignal(int greentime, int redtime, int yellowtime) {
        redTrafficLight = new RedTrafficLight(this, redtime);
        greenTrafficLight = new GreenTrafficLight(this, greentime);
        yellowTrafficLight = new YellowTrafficLight(this, yellowtime);
        this.currentState = greenTrafficLight;
    }

    public void change() {
        this.currentState.update();
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

    public void reportState() {
        System.out.println("Signal state : " + this.currentState);
    }
}
