package com.mylearning.design.retry;

import java.util.function.Supplier;

public class RetryCommand<T> {
    private int retryCounter;
    private final int maxRetries;

    public RetryCommand(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    // Takes a function and executes it, if fails, passes the function to the retry command
    /*
    Java 8 uses Functional Interfaces, which are interfaces with a single abstract method. The package
    java.util.function defines a number of standard functional interfaces, so most of the time you will
    be able to use one of these. Some example functional interfaces are Function (function with return
    value and input param), Supplier (function with return value but no input param), and Consumer
    (function with input param but no return value). However, if one of these standard functional interfaces
    does not meet your needs you can always define your own. In the following example I use Supplier.
    */
    public T run(Supplier<T> function) {
        try {
            return function.get();
        } catch (Exception e) {
            return retry(function);
        }
    }

    public int getRetryCounter() {
        return retryCounter;
    }

    private T retry(Supplier<T> function) throws RuntimeException {
        System.out.println("FAILED - Command failed, will be retried " + maxRetries + " times.");
        retryCounter = 0;
        while (retryCounter < maxRetries) {
            try {
                return function.get();
            } catch (Exception ex) {
                retryCounter++;
                System.out.println("FAILED - Command failed on retry " + retryCounter + " of " + maxRetries + " error: " + ex );
                if (retryCounter >= maxRetries) {
                    System.out.println("Max retries exceeded.");
                    break;
                }
            }
        }
        throw new RuntimeException("Command failed on all of " + maxRetries + " retries");
    }
}