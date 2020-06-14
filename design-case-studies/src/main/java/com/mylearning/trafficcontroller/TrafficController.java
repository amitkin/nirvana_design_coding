package com.mylearning.trafficcontroller;

import java.util.ArrayList;
import java.util.List;

/*
TrafficController will issue the command for Roads and then all TrafficSignal are autonomous and independent from each other to
perform their job(since assigning task to TrafficSignal is handle by TrafficController).
This way if someone will add new Road or remove Road then it will follow open/closed principle along with change is the traffic signal colors.
*/
public class TrafficController {
    List<Road> roads = new ArrayList<>();

    public void signalTraffic() {
        for(Road road : roads) {
            road.startSignal();
        }
    }

    public void populateRoads() {
        roads = new ArrayList<>();
    }

    public static void main(String[] args) {
        TrafficController trafficController = new TrafficController();
        trafficController.populateRoads();
        trafficController.signalTraffic();
    }
}
