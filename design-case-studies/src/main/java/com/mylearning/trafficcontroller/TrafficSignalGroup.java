package com.mylearning.trafficcontroller;

import java.util.List;

public class TrafficSignalGroup {
    TrafficSignal trafficSignalN, trafficSignalS, trafficSignalE, trafficSignalW;

    public TrafficSignalGroup() {
        this.trafficSignalN = null;
        this.trafficSignalS = null;
        this.trafficSignalE = null;
        this.trafficSignalW = null;
    }

    public void grayToFlash() {

    }

    public void rgbToAll() {
        /*%% green for NS on road crossing
        obj=green2NS(obj);
        pause(3)

        %% yellow for all
        obj=yellow2all(obj);
        pause(1)

        %% green for WE on road crossing
        obj=green2WE(obj);
        pause(4)

        %% yellow for all
        obj=yellow2all(obj);
        pause(1)*/
    }

    private void green2NS() {
        /*trafficSignalN.change();=red(obj.lampN);obj.lampS=red(obj.lampS);
        obj.lampE=green(obj.lampE);obj.lampW=green(obj.lampW);*/
    }

    public void init() {
        while(true) {
            //Rotate the traffic signals and

        }
    }
}
