package com.mylearning.design.patterns.pluralsight.behavioral.mediator;

// concrete Command
public class TurnsOnAllLightsCommand implements Command {

    private Mediator lightsMediator;

    public TurnsOnAllLightsCommand(Mediator lightsMediator) {
        this.lightsMediator = lightsMediator;
    }

    @Override
    public void execute() {
        lightsMediator.turnOnAllLights();
    }
}
