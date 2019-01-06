package com.mylearning.linkedlist;

import com.mylearning.linkedlist.LinkedList.ListNode;

public class SortLinkedList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0), p = l;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return l.next;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.append(1);
        linkedList.append(3);
        linkedList.append(2);
        linkedList.append(5);
        linkedList.append(4);
        linkedList.append(6);
        linkedList.append(5);

        linkedList.display();
        SortLinkedList sortLinkedList = new SortLinkedList();
        sortLinkedList.sortList(linkedList.root);
        linkedList.display();
    }
}
