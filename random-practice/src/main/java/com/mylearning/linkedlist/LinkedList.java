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

    public ListNode reverseIterative(ListNode head){
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


    public ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode reverseListHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return reverseListHead;
    }

    public ListNode reverseK(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode tail = head;

        while(n >= k) {
            for (int i = 1; i < k; i++) {
                //Changing pointers as below in one iteration - one link reversed
                //2->1->3, head=2, tail=1, next=3
                //so this needs to be done k-1 times
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            //Moving prev to end of first chunk reversed
            //and tail to the beginning of second chunk
            prev = tail;
            tail = tail.next;
            n -= k;
        }
        return dummy.next;
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

    public ListNode middle(ListNode head) {
        ListNode slow, fast;
        slow = head;
        fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int d){
            data = d;
            next = null;
        }
    }

    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |
     * begin       end
     * after call begin = reverse(begin, end)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *      begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null || head.next ==null || k==1)
            return head;
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        ListNode begin = dummyhead;
        int i=0;
        while (head != null){
            i++;
            if (i%k == 0){
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyhead.next;

    }

    public ListNode reverse(ListNode begin, ListNode end){
        ListNode curr = begin.next;
        ListNode next, first;
        ListNode prev = begin;
        first = curr;
        while (curr!=end){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        /*linkedList.append(1);
        linkedList.append(2);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(4);
        linkedList.append(5);*/
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(9);

        linkedList.deleteDups();
        linkedList.display();

        linkedList.insertAfter(linkedList.get(4), 6);
        linkedList.addInFront(0);
        linkedList.append(7);
        linkedList.display();
        linkedList.root = linkedList.reverseIterative(linkedList.root);
        linkedList.display();
        linkedList.root = linkedList.reverseRecursive(linkedList.root);
        linkedList.display();
        linkedList.root = linkedList.reverseK(linkedList.get(0), 3); //Modifies the root
        linkedList.display();
    }
}
