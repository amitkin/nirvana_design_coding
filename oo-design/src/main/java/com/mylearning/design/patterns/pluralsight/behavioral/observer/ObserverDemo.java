package com.mylearning.design.patterns.pluralsight.behavioral.observer;

public class ObserverDemo {

    public static void main(String[] args) {
        Subject subject = new MessageStream();

        PhoneClient phoneClient = new PhoneClient(subject);
        TabletClient tabletClient = new TabletClient(subject);

        phoneClient.addMessage("a phone message");
        tabletClient.addMessage("another message from a tablet");
        phoneClient.addMessage("last phone message");
    }
}
