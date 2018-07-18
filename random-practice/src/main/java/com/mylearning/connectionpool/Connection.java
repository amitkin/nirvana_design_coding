package com.mylearning.connectionpool;

public interface Connection {
    void close();

    // this is the least interesting part to this problem
    Object execute(Object query);
}
