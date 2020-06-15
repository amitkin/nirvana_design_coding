package com.mylearning.trafficcontroller;

public class TrafficSignalGroup {

    TrafficSignal trafficSignalN, trafficSignalS, trafficSignalE, trafficSignalW;

    public TrafficSignalGroup() {
        this.trafficSignalN = null;
        this.trafficSignalS = null;
        this.trafficSignalE = null;
        this.trafficSignalW = null;
    }

    public void rgbToAll() {
        /*
        //green for NS on road crossing
        obj=green2NS(obj);
        pause(3)

        //yellow for all
        obj=yellow2All(obj);
        pause(1)

        //green for WE on road crossing
        obj=green2WE(obj);
        pause(4)

        //yellow for all
        obj=yellow2All(obj);
        pause(1)
        */
    }

    private void gray2Flash() {
        //trafficSignalN yellow
        //trafficSignalS yellow
        //trafficSignalE yellow
        //trafficSignalW yellow
        //pause(2)
        //yellow2Gray()
        //pause(1)
    }

    private void gray2All() {
        //trafficSignalN gray
        //trafficSignalS gray
        //trafficSignalE gray
        //trafficSignalW gray
    }

    private void yellow2All() {
        //trafficSignalN yellow
        //trafficSignalS yellow
        //trafficSignalE yellow
        //trafficSignalW yellow
    }

    private void yellow2Gray() {
        //trafficSignalN gray
        //trafficSignalS gray
        //trafficSignalE gray
        //trafficSignalW gray
    }

    private void green2NS() {
        //trafficSignalN red
        //trafficSignalS red
        //trafficSignalE green
        //trafficSignalW green
    }

    private void green2WE() {
        //trafficSignalN green
        //trafficSignalS green
        //trafficSignalE red
        //trafficSignalW red
    }

    public void init() {
        /*
        for i=1:2
            lampsAll=gray2Flash(lampsAll);
        end
        for i=1:2
            lampsAll=rgbToAll(lampsAll);
        end
        lampsAll=gray2Flash(lampsAll);
        */
    }
}
