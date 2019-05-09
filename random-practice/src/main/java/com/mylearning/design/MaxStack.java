package com.mylearning.design;

import java.util.LinkedList;
import java.util.TreeMap;

/*
This solution uses TreeMap and Doubly Linked List. TreeMap acts like a priority queue, but it provides O(logN) write. Doubly Linked List provides O(1) push() and pop()

This solution provides O(logN) time for push(), as the TreeMap need to rebalance when insert. And O(logN) for pop() and popMax(), as TreeMap need to rebalance when remove an element. (Note when there are a lot of duplicate elements pushed, the time for these two operations are actually O(1) average, since the remove() is not invoked every time.)
top() and peekMax() are obviously O(1).
*/
class MaxStack {

    private static class ListNode {
        public ListNode prev, next;
        public int value;

        public ListNode(int val) {
            this.value = val;
        }
    }

    private final ListNode head, tail;
    private final TreeMap<Integer, LinkedList<ListNode>> map = new TreeMap<>();

    /** initialize your data structure here. */
    public MaxStack() {
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    //O(log(N))
    public void push(int x) {
        ListNode node = new ListNode(x);
        ListNode last = tail.prev;
        last.next = node;
        node.prev = last;
        node.next = tail;
        tail.prev = node;
        map.computeIfAbsent(x, k -> new LinkedList<>()).add(node);
    }

    //O(log(N))
    public int pop() {
        ListNode last = tail.prev;
        deleteNode(last);
        // since it's pop(), we are always sure that the last element in the map's value list will be the tail.prev
        map.get(last.value).removeLast();
        if (map.get(last.value).isEmpty()) {
            map.remove(last.value);
        }
        return last.value;
    }

    //O(1)
    public int top() {
        return tail.prev.value;
    }

    //O(1)
    public int peekMax() {
        return map.lastKey();
    }

    //O(log(N))
    public int popMax() {
        int max = peekMax();
        ListNode node = map.get(max).removeLast();
        deleteNode(node);
        if (map.get(max).isEmpty()) {
            map.remove(max);
        }
        return max;
    }

    private void deleteNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);
        System.out.println(maxStack.top());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.top());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.top());
    }
}

