package com.mylearning.trafficcontroller;

/*
TrafficController will issue the command for Roads and then all TrafficSignal are autonomous and independent from each other to
perform their job(since assigning task to TrafficSignal is handle by TrafficController).
This way if someone will add new Road or remove Road then it will follow open/closed principle along with change is the traffic signal colors.

To initialize the timing of the traffic light, we need to know the duration of the three light cycles. For the light
to function properly, the duration of the red light should be the sum of the green and yellow light in the opposite
direction. Knowing that the yellow light has a fixed duration of six cycles the user only needs to specify the
duration of green light in two directions (north/south vs. east/west).
*/
public class TrafficController {

    Road roadN, roadS, roadE, roadW;

    public void signalTraffic() {
        roadN.startSignal();
        roadS.startSignal();
        roadE.startSignal();
        roadW.startSignal();
    }

    public void populateRoads() {
        roadN = new Road(2,2,6);
        roadS = new Road(2, 2, 6);
        roadE = new Road(2, 2, 6);
        roadW = new Road(2, 2, 6);
    }

    public static void main(String[] args) {
        TrafficController trafficController = new TrafficController();
        trafficController.populateRoads();
        trafficController.signalTraffic();
    }
}
