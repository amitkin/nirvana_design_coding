package com.mylearning.logger;

public class Main {

    //https://thispointer.com/designing-a-configurable-logging-framework-using-observer-design-pattern/
    /*
    Use cases:
    1. Logging at different granularities
    2. logging to different output channel
    3. specifying package to enable/disable
    */
    public static void main(String[] args) {
        Logger logger = new Logger();

        ConsoleLoggingPlatform consoleLoggingPlatform = new ConsoleLoggingPlatform();
        FlatFileLoggingPlatform fileLoggingPlatform = new FlatFileLoggingPlatform("temp");
        NetworkLoggingPlatform networkLoggingPlatform = new NetworkLoggingPlatform();

        logger.attach(LogLevel.ERROR, consoleLoggingPlatform);
        logger.attach(LogLevel.WARNING, consoleLoggingPlatform);
        logger.attach(LogLevel.INFO, consoleLoggingPlatform);
        logger.attach(LogLevel.ERROR, fileLoggingPlatform);
        logger.attach(LogLevel.INFO, networkLoggingPlatform);

        logger.setLevel(LogLevel.ALL);
        logger.log(LogLevel.ERROR, "", "This is first error");
        logger.log(LogLevel.WARNING, "", "This is first warning");
        logger.log(LogLevel.INFO, "", "This is first info message");

        System.out.println();
        logger.setLevel(LogLevel.ERROR);
        logger.log(LogLevel.ERROR, "", "This is second error");
        logger.log(LogLevel.WARNING, "", "This is second warning");
        logger.log(LogLevel.INFO, "", "This is second info message");

        System.out.println();
        logger.deattach(consoleLoggingPlatform);
        logger.log(LogLevel.ERROR, "", "This is third error");
        logger.log(LogLevel.WARNING, "", "This is third warning");
        logger.log(LogLevel.INFO, "", "This is third info message");
    }
}
