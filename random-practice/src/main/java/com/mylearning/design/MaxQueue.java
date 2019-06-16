package com.mylearning.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MaxQueue<T extends Comparable<T>> {

    private Queue<T> entries = new ArrayDeque<>();
    private Deque<T> candidatesForMax = new ArrayDeque<>();

    public void enqueue(T x) {
        entries.add(x);
        while (!candidatesForMax.isEmpty() && candidatesForMax.peekLast().compareTo(x) < 0) {
            // Eliminate dominated elements in candidatesForMax.
            candidatesForMax.removeLast();
        }
        candidatesForMax.addLast(x);
    }

    public T dequeue() {
        if (!entries.isEmpty()) {
            T result = entries.remove();
            if (result.equals(candidatesForMax.peekFirst())) {
                candidatesForMax.removeFirst();
            }
            return result;
        }
        throw new NoSuchElementException("Called dequeue() on empty queue.");
    }

    public T max() {
        if (!candidatesForMax.isEmpty()) {
            return candidatesForMax.peekFirst();
        }
        throw new NoSuchElementException("empty queue");
    }

    public T head() {
        return entries.peek();
    }

    public static void main(String[] args) {
        MaxQueue<Integer> maxQueue = new MaxQueue<>();
        maxQueue.enqueue(5);
        maxQueue.enqueue(1);
        maxQueue.enqueue(5);
        System.out.println(maxQueue.head());
        System.out.println(maxQueue.dequeue());
        System.out.println(maxQueue.head());
    }
}
