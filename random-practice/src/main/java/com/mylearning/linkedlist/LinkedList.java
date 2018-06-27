package com.mylearning.linkedlist;

import java.util.ArrayList;
import java.util.Hashtable;

public class LinkedList {

    public ListNode root;

    public LinkedList() {
        root = null;
    }

    private ListNode get(int n){
        ListNode temp = root;
        while(n-- > 0 && temp != null){
            temp = temp.next;
        }
        return temp;
    }

    public void addInFront(int d){
        ListNode new_node = new ListNode(d);
        if(root == null)
            root = new_node;
        new_node.next = root;
        root = new_node;
    }

    public void append(int d){
        ListNode new_node = new ListNode(d);
        if(root == null)
            root = new_node;
        else{
            //Creating temp node is important, if you use root it will move ahead
            ListNode temp = root;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new_node;
        }
    }

    public void insertAfter(ListNode prev_node, int data){
        if(prev_node == null)
            return;
        ListNode new_node = new ListNode(data);
        new_node.next = prev_node.next;
        prev_node.next = new_node;
    }

    public void deleteDups(){
        Hashtable<Integer, Boolean> hashtable = new Hashtable<>();
        ListNode temp = root;
        ListNode previous = root;

        while(temp != null){
            if(hashtable.containsKey(temp.data)){
                //deleting duplicate
                previous.next = temp.next;
            } else{
                hashtable.put(temp.data, true);
                previous = temp;
            }
            temp = temp.next;
        }
    }

    public void display(){
        ListNode temp = root;
        while(temp != null){
            System.out.print(temp.data);
            temp = temp.next;
            if(temp != null)
                System.out.print("->");
        }
        System.out.print("\n");
    }

    public void display(ListNode node){
        ListNode temp = node;
        while(temp != null){
            System.out.print(temp.data);
            temp = temp.next;
            if(temp != null)
                System.out.print("->");
        }
        System.out.print("\n");
    }

    public void reverse(){
        if(root == null || root.next == null)
            return;

        ListNode p = root;
        ListNode q = p.next;
        p.next = null; //root now has only 1 node, rest is with q
        ListNode r;

        while(q != null){
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        root = p;

        /*ListNode q = root;
        ListNode r;
        ListNode p = null;

        while(q != null){
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        root = p;*/
    }

    public ListNode reverseK(ListNode head, int k) {
        if (head == null || head.next == null || k <2)
            return head;

        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;
        int count = 0;

        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        //current is at kth position and next at k+1, recursive call for list start from current
        if(current != null)
            head.next = reverseK(current, k);
        return prev;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode p = head;
        ListNode q = p.next;
        p.next = null;
        ListNode r;

        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
    }

    //https://leetcode.com/problems/intersection-of-two-linked-lists/description/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null)
            return null;

        new ArrayList<>();
        //Important to make a copy then iterate
        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }
        return b;
    }

    //Partition around a value. Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5
    public ListNode partition(ListNode head, int x) {
        ListNode smallerHead = new ListNode(0), greaterHead = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode smallerLast = smallerHead, greaterLast = greaterHead;      //current tails of the two queues;
        while (head != null) {
            if (head.data < x) {
                smallerLast.next = head;
                smallerLast = smallerLast.next;
            } else {
                greaterLast.next = head;
                greaterLast = greaterLast.next;
            }
            head = head.next;
        }
        greaterLast.next = null;
        smallerLast.next = greaterHead.next; //Skipping dummy head and linking
        return smallerHead.next;
    }

    //Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {

            ListNode odd = head, even = head.next, evenHead = even;

            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    public static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int d){
            data = d;
            next = null;
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(4);
        linkedList.append(5);

        linkedList.deleteDups();
        linkedList.display();
        ListNode reversedNode = linkedList.reverseK(linkedList.get(0), 3); //Modifies the root
        linkedList.display(reversedNode);

        linkedList.insertAfter(linkedList.get(4), 6);
        linkedList.addInFront(0);
        linkedList.display();
        linkedList.reverse();
        linkedList.display();

    }
}
