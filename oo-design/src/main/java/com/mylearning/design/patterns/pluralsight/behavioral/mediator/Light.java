package com.mylearning.design.patterns.pluralsight.behavioral.mediator;

public class Light {
    private boolean isOn = false;
    private String location = "";

    public Light() {

    }

    public Light(String location) {
        this.location = location;
    }

    public boolean isOn() {
        return isOn;
    }

    public void toggle() {
        if (isOn) {
            off();
        } else {
            on();
        }
    }

    private void off() {
        isOn = false;
        System.out.println(location + " light has been turned off.");
    }

    private void on() {
        isOn = true;
        System.out.println(location + " light has been turned on.");
    }
}
