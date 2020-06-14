package com.mylearning.trafficcontroller;

/*
All the TrafficSignal will have different timer for switching lights(which is G->Y->R->G).
So constructor of TrafficSignal should accept three parameters for light’s timing. So that,
it should be like TrafficSignal(int greentime, int redtime, int yellowtime).

To initialize the timing of the traffic light, we need to know the duration of the three light cycles. For the light
to function properly, the duration of the red light should be the sum of the green and yellow light in the opposite
direction. Knowing that the yellow light has a fixed duration of six cycles the user only needs to specify the
duration of green light in two directions (north/south vs. east/west).
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
