package com.mylearning.lld.trafficsignal;

public class TrafficSensor {
    private String wayId;
    private int trafficDensity; // Simulated traffic density (e.g., number of vehicles)

    public TrafficSensor(String wayId) {
        this.wayId = wayId;
        this.trafficDensity = 0;
    }

    public String getWayId() {
        return wayId;
    }

    public void setTrafficDensity(int density) {
        this.trafficDensity = density;
    }

    public int getTrafficDensity() {
        return trafficDensity;
    }

    public int getSignalDuration() {
        // Return 60 seconds for high traffic, otherwise 40 seconds
        return trafficDensity > 10 ? 60 : 40;
    }
}

