package com.mylearning.lld.trafficsignal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IntersectionController {
    private String id;
    private Map<String, TrafficLight> trafficLights;
    private boolean manualOverride; // Indicates if manual control is active

    public IntersectionController(String id) {
        this.id = id;
        this.trafficLights = new HashMap<>();
        this.manualOverride = false;
    }

    public void addTrafficLight(TrafficLight light) {
        trafficLights.put(light.getId(), light);
    }

    public void enableManualOverride() {
        manualOverride = true;
        System.out.println("Manual override enabled.");
    }

    public void disableManualOverride() {
        manualOverride = false;
        System.out.println("Manual override disabled. Returning to automated control.");
    }

    public void manuallyControlSignal(String lightId, LightState state, int duration) {
        if (manualOverride) {
            if (trafficLights.containsKey(lightId)) {
                TrafficLight light = trafficLights.get(lightId);
                light.changeState(state);
                System.out.println("Manually changed " + lightId + " to " + state + " for " + duration + " seconds.");
            } else {
                System.out.println("Traffic Light " + lightId + " not found.");
            }
        } else {
            System.out.println("Manual override is not enabled. Please enable it first.");
        }
    }

    public void automatedControl() {
        for (TrafficLight light : trafficLights.values()) {
            int duration = calculateDuration();

            if (light.getState() == LightState.RED) {
                light.changeState(LightState.GREEN);
                System.out.println(light.getId() + " is GREEN for " + duration + " seconds.");
            } else if (light.getState() == LightState.GREEN) {
                light.changeState(LightState.YELLOW); // Fixed yellow duration
                System.out.println(light.getId() + " is YELLOW for 5 seconds.");
            } else {
                light.changeState(LightState.RED); // Default red duration
                System.out.println(light.getId() + " is RED for 60 seconds.");
            }
        }
    }

    private int calculateDuration() {
        int density = new Random().nextInt(100) + 1;
        if (density > 75) {
            return 90; // High density, longer green light
        } else if (density > 50) {
            return 60; // Moderate density
        } else {
            return 30; // Low density, shorter green light
        }
    }
}

