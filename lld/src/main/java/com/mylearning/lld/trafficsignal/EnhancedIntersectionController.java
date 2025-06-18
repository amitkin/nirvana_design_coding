package com.mylearning.lld.trafficsignal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class EnhancedIntersectionController {
    private Queue<String> trafficFlowQueue; // FIFO queue for traffic ways
    private Map<String, TrafficLight> trafficLights;
    private Map<String, TrafficSensor> trafficSensors;

    public EnhancedIntersectionController() {
        this.trafficFlowQueue = new LinkedList<>();
        this.trafficLights = new HashMap<>();
        this.trafficSensors = new HashMap<>();
    }

    // Add a traffic way with a light and a sensor
    public void addTrafficWay(String wayId) {
        trafficLights.put(wayId, new TrafficLight(wayId));
        trafficSensors.put(wayId, new TrafficSensor(wayId));
        trafficFlowQueue.offer(wayId); // Add to FIFO queue
    }

    public void setTrafficDensity(String wayId, int density) {
        if (trafficSensors.containsKey(wayId)) {
            trafficSensors.get(wayId).setTrafficDensity(density);
        }
    }

    // Automated signal switching
    public void automatedControl() {
        System.out.println("Starting automated control...");
        while (!trafficFlowQueue.isEmpty()) {
            String currentWay = trafficFlowQueue.poll(); // Get the next way in FIFO
            TrafficLight light = trafficLights.get(currentWay);
            TrafficSensor sensor = trafficSensors.get(currentWay);

            if (light != null && sensor != null) {
                int duration = sensor.getSignalDuration();
                light.changeState(LightState.GREEN);
                System.out.println("Way " + currentWay + " is GREEN for " + duration + " seconds.");
                try {
                    Thread.sleep(duration * 1000L); // Simulate green light duration
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                light.changeState(LightState.YELLOW);
                System.out.println("Way " + currentWay + " is YELLOW for 5 seconds.");
                try {
                    Thread.sleep(5 * 1000L); // Simulate yellow light duration
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                light.changeState(LightState.RED);
                System.out.println("Way " + currentWay + " is RED.");
                trafficFlowQueue.offer(currentWay); // Re-add way to the end of the queue
            }
        }
    }
}

