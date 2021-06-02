package com.mylearning.logger;

public class NetworkLoggingPlatform implements Observer {

    @Override
    public void writeMessage(Subject subject) {
        System.out.println("Network : " + subject.getData());
    }
}
