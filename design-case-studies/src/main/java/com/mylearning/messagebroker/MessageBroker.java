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

            if(i%2 == 0)
                exchange.addAndRoute(i,"even,number");
            else
                exchange.addAndRoute(i,"odd,number");
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

    Broker(Exchange e, Producer p){
        this.exchange = e;
        this.exchange.producer = p;
    }
    void bindToExchange(Queue q, Consumer c, String rq){
        MyQueue mq = new MyQueue(rq, q, c);
        exchange.bindConsumer(mq);
    }
}

public class MessageBroker {
    public static void main(String[] args) {
        Exchange exchange = new Exchange("rabbitmq");
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
