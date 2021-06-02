package com.mylearning.logger;

public class FlatFileLoggingPlatform implements Observer {

    private String fileName;

    public FlatFileLoggingPlatform(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeMessage(Subject subject) {
        System.out.println("File : " + subject.getData());
    }
}
