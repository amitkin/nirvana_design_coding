package com.mylearning.messagequeue.subscriber;

import com.mylearning.messagequeue.model.Message;

public interface Subscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
