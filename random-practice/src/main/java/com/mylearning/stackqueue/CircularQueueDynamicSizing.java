package com.mylearning.stackqueue;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

//A brute-force approach is to use an array, with the head always at index 0.
//An additional variable tracks the index of the tail element. Enqueue has 0(1) time complexity.
//However dequeue's time complexity is 0(n), where n is the number of elements in the queue,
//since every element has to be left-shifted to fill up the space created at index 0.

//A better approach is to keep one more variable to track the head. This way, dequeue can also be performed in O(1) time.
//When performing an enqueue into a full array, we need to resize the array.

public class CircularQueueDynamicSizing {

    private int head = 0, tail = 0, numQueueElements = 0;
    private static final int SCALE_FACTOR = 2;
    private Integer[] entries;

    public CircularQueueDynamicSizing(int capacity) {
        entries = new Integer[capacity];
    }

    public void enqueue(Integer x) {
        if (numQueueElements == entries.length) { // Need to resize
            // Makes the queue elements appear consecutively.
            Collections.rotate(Arrays.asList(entries), -head);
            // Resets head and tail.
            head = 0;
            tail = numQueueElements;
            entries = Arrays.copyOf(entries, numQueueElements * SCALE_FACTOR);
        }
        entries[tail] = x;
        tail = (tail + 1) % entries.length;
        ++numQueueElements;
    }

    public Integer dequeue(){
        if (numQueueElements != 0) {
            --numQueueElements ;
            Integer ret = entries[head];
            head = (head + 1) % entries.length ;
            return ret;
        }
        throw new NoSuchElementException("Dequeue called on an empty queue.");
    }

    public int size() {
        return numQueueElements;
    }

    public static void main(String[] args) {
        CircularQueueDynamicSizing circularQueueDynamicSizing = new CircularQueueDynamicSizing(6);
        circularQueueDynamicSizing.enqueue(5);
        circularQueueDynamicSizing.enqueue(6);
        circularQueueDynamicSizing.enqueue(7);
        circularQueueDynamicSizing.enqueue(8);
        circularQueueDynamicSizing.enqueue(9);
        circularQueueDynamicSizing.dequeue();
        circularQueueDynamicSizing.dequeue();
        circularQueueDynamicSizing.enqueue(10);
        circularQueueDynamicSizing.enqueue(11);
        circularQueueDynamicSizing.enqueue(12);
    }
}
