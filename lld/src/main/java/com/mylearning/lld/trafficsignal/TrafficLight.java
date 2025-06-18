package com.mylearning.lld.trafficsignal;

import lombok.Getter;

public class TrafficLight {
    @Getter
    private String id;
    @Getter
    private LightState state;

    public TrafficLight(String id) {
        this.id = id;
        this.state = LightState.RED;
    }

    public void changeState(LightState state) {
        this.state = state;
        System.out.println("Traffic Light " + id + " is now " + state);
    }

}


