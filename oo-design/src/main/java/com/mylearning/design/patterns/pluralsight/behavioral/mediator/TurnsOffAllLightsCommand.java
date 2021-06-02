package com.mylearning.design.patterns.pluralsight.behavioral.mediator;

// concrete Command
public class TurnsOffAllLightsCommand implements Command {

    private Mediator lightsMediator;

    public TurnsOffAllLightsCommand(Mediator lightsMediator) {
        this.lightsMediator = lightsMediator;
    }

    @Override
    public void execute() {
        lightsMediator.turnOffAllLights();
    }
}
