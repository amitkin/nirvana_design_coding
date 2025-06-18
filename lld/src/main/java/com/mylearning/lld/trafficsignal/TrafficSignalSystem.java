package com.mylearning.lld.trafficsignal;

import java.util.HashMap;
import java.util.Map;

public class TrafficSignalSystem {
    private Map<String, IntersectionController> intersections; // Intersections managed by the system
    private boolean systemManualOverride; // System-wide manual override flag

    public TrafficSignalSystem() {
        this.intersections = new HashMap<>();
        this.systemManualOverride = false;
    }

    // Add an intersection to the system
    public void addIntersection(String id, IntersectionController controller) {
        intersections.put(id, controller);
    }

    // Enable system-wide manual override
    public void enableSystemManualOverride() {
        systemManualOverride = true;
        System.out.println("System-wide manual override enabled.");
        for (IntersectionController controller : intersections.values()) {
            controller.enableManualOverride();
        }
    }

    // Disable system-wide manual override
    public void disableSystemManualOverride() {
        systemManualOverride = false;
        System.out.println("System-wide manual override disabled. Returning to automated control.");
        for (IntersectionController controller : intersections.values()) {
            controller.disableManualOverride();
        }
    }

    // Manually control a specific traffic light in a specific intersection
    public void manuallyControlSignal(String intersectionId, String lightId, LightState state, int duration) {
        if (systemManualOverride) {
            IntersectionController controller = intersections.get(intersectionId);
            if (controller != null) {
                controller.manuallyControlSignal(lightId, state, duration);
            } else {
                System.out.println("Intersection " + intersectionId + " not found.");
            }
        } else {
            System.out.println("System-wide manual override is not enabled. Enable it first.");
        }
    }

    // Perform automated control for all intersections
    public void performAutomatedControl() {
        if (!systemManualOverride) {
            System.out.println("Performing automated control for all intersections...");
            for (IntersectionController controller : intersections.values()) {
                controller.automatedControl();
            }
        } else {
            System.out.println("Automated control is paused due to system-wide manual override.");
        }
    }
}
