package com.mylearning.messagequeue.handler;

import com.mylearning.messagequeue.model.Message;
import com.mylearning.messagequeue.model.Topic;
import com.mylearning.messagequeue.model.SubscriberOffset;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;

@Getter
public class SubscriberWorker implements Runnable {

    private final Topic topic;
    private final SubscriberOffset subscriberOffset;

    public SubscriberWorker(@NonNull final Topic topic, @NonNull final SubscriberOffset subscriberOffset) {
        this.topic = topic;
        this.subscriberOffset = subscriberOffset;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (subscriberOffset) {
            do {
                int curOffset = subscriberOffset.getOffset().get();
                while (curOffset >= topic.getMessages().size()) {
                    subscriberOffset.wait();
                }
                Message message = topic.getMessages().get(curOffset);
                subscriberOffset.getSubscriber().consume(message);

                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                subscriberOffset.getOffset().compareAndSet(curOffset, curOffset + 1);
            } while (true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (subscriberOffset) {
            subscriberOffset.notify();
        }
    }
}
