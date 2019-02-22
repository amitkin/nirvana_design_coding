package com.mylearning.filesystem;

public class CommandDefinition {
    Command command;
    String parameter = "";

    public CommandDefinition(Command command, String parameter) {
        this.command = command;
        this.parameter = parameter;
    }

    public CommandDefinition(Command command) {
        this.command = command;
    }
}
