package com.mylearning.design;

//https://leetcode.com/problems/design-hashmap/
public class MyHashMap {
    /*
    Some of the questions which can be asked to the interviewer before implementing the solution
    For simplicity, are the keys integers only?
    For collision resolution, can we use chaining?
    Do we have to worry about load factors?
    Can we assume inputs are valid or do we have to validate them?
    Can we assume this fits memory?
    */
    final ListNode[] nodes = new ListNode[10000];

    private static class ListNode
    {
        int key, val;
        ListNode next;

        ListNode(int key, int val)
        {
            this.key = key;
            this.val = val;
        }
    }

    public int get(int key)
    {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);
        return prev.next == null ? -1 : prev.next.val;
    }

    public void put(int key, int value)
    {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);

        if (prev.next == null)
            prev.next = new ListNode(key, value);
        else
            prev.next.val = value;
    }

    public void remove(int key)
    {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);

        if(prev.next != null)
            prev.next = prev.next.next;
    }

    private int getIndex(int key)
    {
        return Integer.hashCode(key) % nodes.length;
    }

    private ListNode findElement(int index, int key)
    {
        if(nodes[index] == null)
            return nodes[index] = new ListNode(-1, -1);

        ListNode prev = nodes[index];

        while(prev.next != null && prev.next.key != key)
        {
            prev = prev.next;
        }
        return prev;
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 2);
        map.get(1);
        map.remove(1);
    }

}