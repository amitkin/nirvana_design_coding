package com.mylearning.linkedlist;

import com.mylearning.linkedlist.LinkedList.ListNode;

public class ReverseKGroup {

    static class Pair {
        ListNode head;
        ListNode tail;

        public Pair(ListNode head, ListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    //If it is less than k nodes then leave as it is.
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
        ListNode p = begin;
        ListNode q = begin.next;
        ListNode r;

        ListNode first = q;

        //dummy(begin,p)->1(first,q)->2->3->4->5->6
        //1st iteration - dummy(begin)<-1(first,p) 2(q,r)->3->4->5->6
        //2nd iteration - dummy(begin)<-1(first)<-2(p) 3(q,r)->4->5->6
        //3rd iteration - dummy(begin)<-1(first)<-2<-3(p) 4(q,r)->5->6
        while (q!=end) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }

        //3rd iteration - dummy(begin)<-1(first)<-2<-3(p) 4(q,r)->5->6
        begin.next = p; //This step is important for linking 1(begin)->6(p) in next reverse call
        first.next = q; //dummy(begin)->3(p)->2->1(first)->4(q,r)->5->6
        return first; //new begin for next iteration
    }

    public ListNode reverseK(ListNode root, int k) {
        if(root == null || root.next == null || k ==1) {
            return root;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = root;
        ListNode head = root;
        ListNode cur = root;

        //count no of nodes
        int count = countNodes(root);

        int chunks = count/k + 1;
        //In loop call reverse and link reversed chunks

        Pair prevPair = new Pair(dummyNode, dummyNode);
        Pair curPair;

        ListNode result = null;

        for(int i=0 ; i< chunks; i++) {
            int chunksize = k;
            while(cur != null && chunksize > 0) {
                cur = cur.next;
                chunksize--;
            }

            ListNode nextHead = cur;

            if(chunksize > 0) {
                curPair = new Pair(head, cur);
            } else {
                curPair = reverse(head, k);
            }

            if(result == null) {
                result = curPair.head;
            }

            prevPair.tail.next = curPair.head;


            head = nextHead;
            cur = head;
            prevPair = curPair;
        }
        return result;
    }

    private Pair reverse(ListNode head, int chunkSize) {
        if (head == null || head.next == null)
            return new Pair(head, null);

        //this will reverse 3 nodes or till null
        ListNode p = head;
        ListNode q = p.next;
        ListNode r;
        p.next = null;
        int k = chunkSize;
        while(q != null && k > 1) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
            k--;
        }
        //p is head
        return new Pair(p, head);
    }

    private int countNodes(ListNode root) {
        int count = 0;
        ListNode cur = root;
        while(cur.next != null) {
            count++;
            cur = cur.next;
        }
        return count+1;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(5);
        linkedList.append(6);

        linkedList.display();
        //linkedList.display(linkedList.reverseK(linkedList.root, 3));

        ReverseKGroup reverseKGroup = new ReverseKGroup();
        linkedList.display(reverseKGroup.reverseK(linkedList.root, 3));
    }

}
