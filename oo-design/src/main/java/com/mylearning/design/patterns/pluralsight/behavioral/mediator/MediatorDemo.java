package com.mylearning.design.patterns.pluralsight.behavioral.mediator;

public class MediatorDemo {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        Light bedroomLight = new Light("bedroom");
        Light kitchenLight = new Light("kitchen");

        mediator.registerLight(bedroomLight);
        mediator.registerLight(kitchenLight);

        Command turnOnAllLightsCommand = new TurnsOnAllLightsCommand(mediator);
        turnOnAllLightsCommand.execute();

        Command turnOffAllLightsCommand = new TurnsOffAllLightsCommand(mediator);
        turnOffAllLightsCommand.execute();
    }
}
