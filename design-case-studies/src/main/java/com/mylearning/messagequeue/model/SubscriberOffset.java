package com.mylearning.messagequeue.model;

import com.mylearning.messagequeue.subscriber.Subscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class SubscriberOffset {
    private final AtomicInteger offset;
    private final Subscriber subscriber;

    public SubscriberOffset(@NonNull final Subscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }
}
