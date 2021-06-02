package com.mylearning.design.patterns.pluralsight.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverEverydayDemo {

    public static void main(String[] args) {
        TwitterStream ts = new TwitterStream();

        Client client1 = new Client("client 1");
        Client client2 = new Client("client 2");

        ts.addObserver(client1);
        ts.addObserver(client2);

        ts.someoneTweeted();
    }
}

// concrete subject
class TwitterStream extends Observable {

    public void someoneTweeted() {
        setChanged();
        notifyObservers();
    }
}

// concrete observer
class Client implements Observer {
    private String name;

    Client(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update " + name + "'s stream, someone tweeted something");
    }
}
