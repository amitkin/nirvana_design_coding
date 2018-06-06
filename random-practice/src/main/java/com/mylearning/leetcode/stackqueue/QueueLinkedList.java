package com.mylearning.leetcode.stackqueue;

import com.mylearning.leetcode.linkedlist.LinkedList.ListNode;

public class QueueLinkedList {
    ListNode root;

    public QueueLinkedList() {
        this.root = null;
    }

    //Add after root
    public void enqueue(int n){
        ListNode newNode = new ListNode(n);
        if(root == null)
            root = newNode;
        else{
            ListNode temp = root;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public int dequeue(){
        if(isEmpty())
            throw new IllegalStateException("QueueLinkedList is empty!!");
        ListNode temp = root;
        root = root.next;
        System.out.println("Dequeued data " + temp.data + " from queue");
        return temp.data;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int peek(){
        if(isEmpty())
            throw new IllegalStateException("QueueLinkedList is empty!!");
        return root.data;
    }

    public void display(){
        ListNode temp = root;
        while(temp != null){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue(3);
        q.enqueue(1);
        q.enqueue(5);
        q.enqueue(2);
        q.display();
        q.dequeue();
        q.dequeue();
        System.out.println("Front element : " + q.peek());
        q.dequeue();
        q.dequeue();
        q.display();
    }

}
