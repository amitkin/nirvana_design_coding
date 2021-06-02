package com.mylearning.messagebroker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * Concurrent linked Queue
 * simple one producer one exchange multiple queues, one queue to one consumer
 * https://www.cloudamqp.com/blog/a-walk-through-of-the-design-and-architecture-of-rabbitmq.html
 * rabbitmq : channel -> exchange -> queue -> bind queue to exchange -> subscribe consumer to queues
 */
class Sleep {
    static void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//consumer job
class Consumer implements Runnable {
    Queue queue;
    String name;
    boolean hasReceived = false;

    Consumer(String name){
        this.name = name;
        (new Thread(this)).start();
    }
    @Override
    public void run() {
        hasReceived = true;
        while(true){
            Sleep.sleep();
            if(!queue.isEmpty()) {
                System.out.println(name + " reading " + queue.remove());
            }
        }
    }
}

//producer job
class Producer implements Runnable {
    Exchange exchange;

    //Object payload;
    String routingKey;
    //boolean hasSent = true;

    Producer(Exchange e){
        exchange = e;
        (new Thread(this)).start();
    }
    @Override
    public void run() {
        //hasSent = true;
        int i = 0;
        Sleep.sleep();
        while(i < 100){
            if(i%2 == 0) {
                exchange.addAndRoute(i, "evenQueue, numberQueue");
            } else {
                exchange.addAndRoute(i, "oddQueue, numberQueue");
            }
            System.out.println("prod .. "+ i);
            i++;
        }
        System.out.println("prod done ");
    }
}
class MyQueue {
    String routingKey;
    Queue<Object> queue;
    Consumer consumer; //you can count number of consumers of a type and spawn multiple threads

    public MyQueue(String routingKey, Queue<Object> queue, Consumer consumer) {
        this.routingKey = routingKey;
        this.queue = queue;
        this.consumer = consumer;
        consumer.queue = queue; //one to one
    }
}

class Exchange{
    String name;
    Producer producer; //one producer
    Map<String, List<MyQueue>> routingKeyToQueues = new HashMap<>(); //routing queue and list of queues(each having one consumer)

    Exchange(String name){
        this.name = name;
    }

    void bindProducer(Producer producer){
        this.producer = producer;
    }

    void bindConsumer(MyQueue myQueue){
        if(routingKeyToQueues.get(myQueue.routingKey) == null) {
            routingKeyToQueues.put(myQueue.routingKey, new ArrayList<>());
        }
        routingKeyToQueues.get(myQueue.routingKey).add(myQueue);
    }

    void addAndRoute(Object payload, String routingKeys){
        String [] rKeys = routingKeys.split(",");
        for(String key :rKeys){
            List<MyQueue> myQueues = routingKeyToQueues.get(key);
            for (int i = 0; i < myQueues.size(); i++) {
                myQueues.get(i).queue.add(payload);
            }
        }
    }
}

class Broker{
    Exchange exchange;

    Broker(Exchange exchange, Producer producer){
        this.exchange = exchange;
        this.exchange.producer = producer;
    }

    //bind queue and consumer to exchange
    void bindToExchange(Queue queue, Consumer consumer, String routingKey){
        MyQueue mq = new MyQueue(routingKey, queue, consumer);
        exchange.bindConsumer(mq);
    }
}

public class MessageBroker {
    public static void main(String[] args) {
        Exchange exchange = new Exchange("rbmqexchange");
        Producer producer = new Producer(exchange);
        Broker broker = new Broker(exchange, producer);

        Queue oddQueue = new ConcurrentLinkedQueue();
        Consumer oddConsumer = new Consumer("oddQueue");
        broker.bindToExchange(oddQueue, oddConsumer, "oddQueue");

        Queue evenQueue = new ConcurrentLinkedQueue();
        Consumer evenConsumer = new Consumer("evenQueue");
        broker.bindToExchange(evenQueue, evenConsumer, "evenQueue");

        Queue numberQueue = new ConcurrentLinkedQueue();
        Consumer numberConsumer = new Consumer("numberQueue");
        broker.bindToExchange(numberQueue, numberConsumer, "numberQueue");
    }
}
