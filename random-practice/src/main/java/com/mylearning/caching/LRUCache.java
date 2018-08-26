package com.mylearning.caching;

import java.util.HashMap;

public class LRUCache {

    int capacity;
    private HashMap<Integer, Node> map = new HashMap<>();
    private Node head = null;
    private Node tail = null;

    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    private void setHead(Node n){
        System.out.println("Updating position of Node : " + n);
        n.next = head;
        n.prev = null;

        //For one or more nodes scenario
        if(head != null){
            head.prev = n;
        }
        head = n;

        //For zero node scenario
        if(tail == null){
            tail = head;
        }

    }

    private void remove(Node n){
        System.out.println("Removing Node : " + n);
        //Middle node scenario
        if(n.prev != null){
            n.prev.next = n.next;
        }
        //One node scenario
        if(n.next == null){
            head = null;
            tail = null;
        } else{
            //first node scenario
            head = n.next;
            head.prev = null;
        }
    }

    public int get(int key){
        if(map.containsKey(key)){
            //Update the key node to head
            Node n = map.get(key);
            remove(n);
            setHead(n);
        }
        return -1;
    }

    public void set(int key, int value){
        if(map.containsKey(key)){
            //Update the key node to head
            Node n = map.get(key);
            remove(n);
            setHead(n);
        } else{
            Node newNode = new Node(key, value);
            if(map.size() >= capacity){
                map.remove(tail.key);
                //Remove the tail from double linked list
                remove(tail);
            }
            setHead(newNode);
            map.put(key, newNode);
        }
    }

    private class Node {
        private int key;
        private int value;
        private Node next;
        private Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int key() {
            return key;
        }

        public int value() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{key=" + key + ", value=" + value + '}';
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.set(1, 10);
        lruCache.set(2, 15);
        lruCache.set(3, 20);
        lruCache.set(4, 25);
        lruCache.set(5, 30);
        lruCache.map.values().forEach(System.out::println);
        lruCache.set(6, 34);
        lruCache.map.values().forEach(System.out::println);
    }
}
