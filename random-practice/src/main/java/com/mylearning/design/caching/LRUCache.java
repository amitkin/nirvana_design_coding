package com.mylearning.design.caching;

import java.util.HashMap;

public class LRUCache {

    private int count,capacity;
    private HashMap<Integer, Node> cache = new HashMap<>();
    private Node head,tail;

    class Node {
        int key,value;
        Node pre,post;

        @Override
        public String toString() {
            return "Node{key=" + key + ", value=" + value + '}';
        }
    }

    private void addNode(Node node){
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }

    private void removeNode(Node node){
        node.pre.post = node.post;
        node.post.pre = node.pre;
    }

    private void moveToFirst(Node node){
        this.removeNode(node);
        this.addNode(node);
    }

    private Node removeFromLast() {
        Node node = tail.pre;
        removeNode(node);
        return node;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        count = 0;
        head = new Node();
        head.pre = null;
        tail = new Node();
        tail.post = null;
        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            if(node != null){
                this.moveToFirst(node);
                return node.value;
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node != null){
            node.value = value;
            this.moveToFirst(node);
        }else{
            node = new Node();
            node.key = key;
            node.value = value;
            this.cache.put(key,node);
            this.addNode(node);
            this.count++;
            if(count > capacity){
                Node lastNode = this.removeFromLast();
                this.cache.remove(lastNode.key);
                --count;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(2, 2);
        lruCache.cache.values().forEach(System.out::println);
        System.out.println(lruCache.get(2));
        lruCache.put(1, 1);
        lruCache.put(4, 1);
        lruCache.cache.values().forEach(System.out::println);
        System.out.println(lruCache.get(2));
    }
}
