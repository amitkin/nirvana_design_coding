package com.mylearning.leetcode.stackqueue;

import com.mylearning.leetcode.linkedlist.LinkedList.ListNode;

public class StackLinkedList {

    ListNode root;

    public StackLinkedList() {
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    //Add in beginning
    public void push(int n){
        ListNode newNode = new ListNode(n);
        if(root == null)
            root = newNode;
        else{
            newNode.next = root;
            root = newNode;
        }
        System.out.println("Pushed data " + n + " to stackqueue");
    }

    //Remove in beginning
    public int pop(){
        if(isEmpty())
            throw new IllegalStateException("StackLinkedList is empty!!");
        ListNode temp = root;
        root = root.next;
        System.out.println("Popped data " + temp.data + " from stackqueue");
        return temp.data;
    }

    public int peek(){
        if(isEmpty())
            throw new IllegalStateException("StackLinkedList is empty!!");
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
        StackLinkedList s = new StackLinkedList();
        s.push(3);
        s.push(1);
        s.push(5);
        s.push(2);
        s.display();
        s.pop();
        s.pop();
        System.out.println("Top element : " + s.peek());
        s.pop();
        s.pop();
        s.display();
    }
}
