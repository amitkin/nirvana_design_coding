package com.mylearning.lld.cache.exceptions;

public class StorageFullException extends RuntimeException {

    public StorageFullException(String message) {
        super(message);
    }
}
