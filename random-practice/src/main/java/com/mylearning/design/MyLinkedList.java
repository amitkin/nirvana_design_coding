package com.mylearning.design;

//https://leetcode.com/problems/design-linked-list/
public class MyLinkedList {
    class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
    private Node head;
    private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;

        if (head == null)
            return -1;

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new
     node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node temp = new Node(val);
        temp.next = head;
        head = temp;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        size++;
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list,
     the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
        }
        else {
            size++;
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node node = new Node(val);
            node.next = current.next;
            current.next = node;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;

        Node current = head;
        if(index == 0) {
            head = current.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtIndex(-1,0);
        linkedList.get(0);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtTail(4);
        linkedList.addAtIndex(2,3);
        linkedList.deleteAtIndex(-1);
    }
}