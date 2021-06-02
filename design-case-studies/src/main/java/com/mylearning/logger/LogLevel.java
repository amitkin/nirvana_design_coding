package com.mylearning.logger;

public enum LogLevel {
    ERROR(4),
    WARNING(3),
    INFO(2),
    ALL(1);

    private int value;

    private LogLevel(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
};
