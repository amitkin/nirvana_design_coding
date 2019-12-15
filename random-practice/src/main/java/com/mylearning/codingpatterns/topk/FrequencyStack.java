package com.mylearning.codingpatterns.topk;

import java.util.*;

//There is better solution using Map and Stack - https://leetcode.com/problems/maximum-frequency-stack/
public class FrequencyStack {

    //O(n) space for map
    private HashMap<Integer, Integer> freq;
    private HashMap<Integer, Stack<Integer>> mapWithStack;
    private int maxfreq;

    public FrequencyStack() {
        freq = new HashMap<>();
        mapWithStack = new HashMap<>();
        maxfreq = 0;
    }

    //O(1)
    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);
        maxfreq = Math.max(maxfreq, f);
        if (!mapWithStack.containsKey(f)) {
            mapWithStack.put(f, new Stack<>());
        }
        mapWithStack.get(f).add(x);
    }

    /*
    Explanation of if (mapWithStack.get(maxfreq).size() == 0) maxfreq--;.
    Map mapWithStack, stores <frequency, a stack contains all elements which was pushed frequency times>, therefore,
    it's guaranteed that the keys of map mapWithStack will be consecutively increasing, from 1 to maxfreq without gap.
    For example, when all elements that appeared 3 times have been popped out, we start checking the stack
    for all elements that appeared 2 times. In short, an element will appear in all stacks which have a
    key freq <= element's freq.
    */
    //O(1)
    public int pop() {
        int x = mapWithStack.get(maxfreq).pop();
        freq.put(x, maxfreq - 1);
        if (mapWithStack.get(maxfreq).size() == 0) {
            maxfreq--;
        }
        return x;
    }

    //O(n) space for heap and map
    class Element {
        int number;
        int frequency;
        int sequenceNumber;

        public Element(int number, int frequency, int sequenceNumber) {
            this.number = number;
            this.frequency = frequency;
            this.sequenceNumber = sequenceNumber;
        }
    }

    class ElementComparator implements Comparator<Element> {
        public int compare(Element e1, Element e2) {
            if (e1.frequency != e2.frequency)
                return e2.frequency - e1.frequency;
            // if both elements have same frequency, return the one that was pushed later
            return e2.sequenceNumber - e1.sequenceNumber;
        }
    }

    private int sequenceNumber = 0;
    private PriorityQueue<Element> maxHeap = new PriorityQueue<>(new ElementComparator());
    private Map<Integer, Integer> frequencyMap = new HashMap<>();

    //O(logn)
    public void pushUsingPQ(int num) {
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        maxHeap.offer(new Element(num, frequencyMap.get(num), sequenceNumber++));
    }

    //O(logn)
    public int popUsingPQ() {
        Element element = maxHeap.poll();
        if(element != null) {
            int num = element.number;

            // decrement the frequency or remove if this is the last number
            if (frequencyMap.get(num) > 1)
                frequencyMap.put(num, frequencyMap.get(num) - 1);
            else
                frequencyMap.remove(num);

            return num;
        }
        throw new RuntimeException("Exception in popping");
    }

    public static void main(String[] args) {
        FrequencyStack frequencyStack = new FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }
}
