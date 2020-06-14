package com.mylearning.trafficcontroller;

/*
The TrafficLight class is responsible for timing the light changes and signaling the current color. The signaling of
the color is implemented by blocking and clearing the four places in the roads that are just before the entrance to the
intersection. This behavior emulates gates at toll booths - a rather drastic method for enforcing the traffic rules.
However, it greatly simplifies the object interaction.

Green Light- It represents the go or Move state of the vehicles.
Yellow Light- It warns that signal is about to change to Red.
Red Light- It represents Stop state of the vehicles.

Our traffic light design requires that the signal in the two opposite directions (north/south or east/west) are the same
at all times.
 */
public abstract class TrafficLight {

    private int time;

    //The constructor for this class should ask the user to enter the timer settings.
    public TrafficLight(int time) {
        this.time = time;
    }

    //The Update function should advance the traffic light timer, display the new state, and block or clear appropriate road places.
    public abstract void update();

    public int getTime() {
        return this.time;
    }
}
