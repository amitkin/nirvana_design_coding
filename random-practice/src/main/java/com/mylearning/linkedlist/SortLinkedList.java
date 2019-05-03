package com.mylearning.linkedlist;

import com.mylearning.linkedlist.LinkedList.ListNode;

public class SortLinkedList {

    /*
    The time complexity is 0(n2), which corresponds to the case where the list is reverse sorted to begin with.
    The space complexity is 0(1).
    */
    public ListNode insertionSort(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.data > current.next.data) {
                ListNode target = current.next, pre = dummyHead;
                while (pre.next.data < target.data) {
                    pre = pre.next;
                }
                ListNode temp = pre.next;
                pre.next = target;
                current.next = target.next;
                target.next = temp;
            } else {
                current = current.next;
            }
        }
        return dummyHead.next ;
    }

    /*
    Mergesort applied to arrays is a stable 0(n log n) algorithm. However, it is not in-place, since there is no way to merge
    two sorted halves of an array in-place in linear time. Unlike arrays, lists can be merged in-place—conceptually, this is because
    insertion into the middle of a list is an 0(1) operation. The following program implements a mergesort on lists.
    */
    public ListNode mergeSort(ListNode head) {
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
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(slow);

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
        sortLinkedList.mergeSort(linkedList.root);
        linkedList.display();

        sortLinkedList.insertionSort(linkedList.root);
        linkedList.display();
    }
}
