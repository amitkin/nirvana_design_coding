package com.mylearning.design.solid.examples.cache.storage;

import com.mylearning.design.solid.examples.cache.exceptions.NotFoundException;
import com.mylearning.design.solid.examples.cache.exceptions.StorageFullException;

public interface Storage<Key, Value> {
    public void add(Key key, Value value) throws StorageFullException;

    void remove(Key key) throws NotFoundException;

    Value get(Key key) throws NotFoundException;
}
