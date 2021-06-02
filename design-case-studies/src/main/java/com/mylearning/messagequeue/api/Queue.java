package com.mylearning.messagequeue.api;

import com.mylearning.messagequeue.handler.TopicHandler;
import com.mylearning.messagequeue.model.Message;
import com.mylearning.messagequeue.model.Topic;
import com.mylearning.messagequeue.model.SubscriberOffset;
import com.mylearning.messagequeue.subscriber.Subscriber;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private final Map<String, TopicHandler> topicProcessors;

    public Queue() {
        this.topicProcessors = new HashMap<>();
    }

    public Topic createTopic(@NonNull final String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicProcessors.put(topic.getTopicId(), topicHandler);
        System.out.println("Created topic: " + topic.getTopicName());
        return topic;
    }

    public void subscribe(@NonNull final Subscriber subscriber, @NonNull final Topic topic) {
        topic.addSubscriber(new SubscriberOffset(subscriber));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish(@NonNull final Topic topic, @NonNull final Message message) {
        topic.addMessage(message);
        System.out.println(message.getMsg() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicProcessors.get(topic.getTopicId()).publish()).start();
    }

    public void resetOffset(@NonNull final Topic topic, @NonNull final Subscriber subscriber, @NonNull final Integer newOffset) {
        for (SubscriberOffset subscriberOffset : topic.getSubscribersWithOffset()) {
            if (subscriberOffset.getSubscriber().equals(subscriber)) {
                subscriberOffset.getOffset().set(newOffset);
                System.out.println(subscriberOffset.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicProcessors.get(topic.getTopicId()).startSubscriberWorker(subscriberOffset)).start();
                break;
            }
        }
    }
}
