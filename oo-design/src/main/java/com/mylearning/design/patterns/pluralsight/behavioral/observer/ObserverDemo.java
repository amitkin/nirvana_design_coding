package com.mylearning.design.patterns.pluralsight.behavioral.observer;

//https://www.youtube.com/watch?v=Ep9_Zcgst3U
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
