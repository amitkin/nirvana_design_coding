package com.mylearning.logger;

import java.util.Arrays;

/*
Responsibilities:
1. Responsible for receiving different type of log messages like ERROR, WARNING & INFO etc from application.
2. Manages a registry map of Logging Platforms based on log level types.
3. Provides a mechanism to attach and de-attach Logging Platforms with different log level types at runtime.
4. On Receiving message from application, fetches the type of log message and then forwards the message to the Logging
Platforms registered with that log level type.

This is a perfect example of Observer Design Pattern. Define a one-to-many dependency between objects so that when one object changes state,
all its dependents are notified and updated automatically.

Here, Logger is SUBJECT and Logging Platforms are OBSERVERS. It decouples the sender and receivers i.e it decouples the different Application
modules and actual logging platforms.

Logging Platforms registers itself with the Subject (i.e. Logger) on the basis of log level types and when Logger (i.e. Subject) receives
any message it notifies the Platforms registered with that log level type by forwarding the message. Then those platforms take action on that message.
*/
public class Logger extends Subject {

    private LogLevel currentLogLevel;

    public Logger() {
        super();

    }

    public void log(LogLevel logLevel, String namespace, String message) {
        if(isLevelEnabled(logLevel)) {
            setData(message);
            notify(logLevel);
        }
    }

    protected boolean isLevelEnabled(LogLevel logLevel) {
        return (logLevel.getValue() >= currentLogLevel.getValue());
    }

    public void setLevel(LogLevel logLevel) {
        currentLogLevel = logLevel;
    }
}
