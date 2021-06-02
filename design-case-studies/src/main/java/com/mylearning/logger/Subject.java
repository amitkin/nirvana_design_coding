package com.mylearning.logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Subject {
    private Map<LogLevel, List<Observer>> registryMap = new HashMap<>();
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void attach(LogLevel logLevel, Observer observer) {
        List<Observer> observers = registryMap.computeIfAbsent(logLevel, k -> new ArrayList<>());
        observers.add(observer);
        registryMap.put(logLevel, observers);
    }

    public void deattach(Observer observer) {
        //search observer and remove
        for(Map.Entry<LogLevel, List<Observer>> entry : registryMap.entrySet()) {
            entry.getValue().remove(observer);
        }
    }

    public void notify(LogLevel logLevel) {
        //call observer update
        List<Observer> observers = registryMap.get(logLevel);
        if(observers != null && !observers.isEmpty()) {
            for(Observer observer : observers) {
                observer.writeMessage(this);
            }
        }
    }
}
