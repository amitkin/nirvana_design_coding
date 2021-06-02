package com.mylearning.logger;

public class ConsoleLoggingPlatform implements Observer {

    @Override
    public void writeMessage(Subject subject) {
        System.out.println("Console : " + subject.getData());
    }
}
