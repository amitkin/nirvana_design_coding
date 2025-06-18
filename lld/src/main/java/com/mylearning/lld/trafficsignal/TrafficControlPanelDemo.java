package com.mylearning.lld.trafficsignal;

public class TrafficControlPanelDemo {

    public static void main(String[] args) {
        // Create the traffic signal system
        TrafficSignalSystem system = new TrafficSignalSystem();

        // Create intersections
        IntersectionController intersection1 = new IntersectionController("Intersection-1");
        intersection1.addTrafficLight(new TrafficLight("TL1"));
        intersection1.addTrafficLight(new TrafficLight("TL2"));

        IntersectionController intersection2 = new IntersectionController("Intersection-2");
        intersection2.addTrafficLight(new TrafficLight("TL3"));
        intersection2.addTrafficLight(new TrafficLight("TL4"));

        // Add intersections to the system
        system.addIntersection("Intersection-1", intersection1);
        system.addIntersection("Intersection-2", intersection2);

        // Perform automated control
        system.performAutomatedControl();

        // Enable system-wide manual override
        system.enableSystemManualOverride();
        system.manuallyControlSignal("Intersection-1", "TL1", LightState.GREEN, 45);
        system.manuallyControlSignal("Intersection-2", "TL4", LightState.RED, 30);

        // Attempt automated control while manual override is active
        system.performAutomatedControl();

        // Disable system-wide manual override
        system.disableSystemManualOverride();
        system.performAutomatedControl();
    }

}
