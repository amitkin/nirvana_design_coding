package com.mylearning.leetcode.stackqueue;

public class TwoStacksSingleArray {
    private int arr[];
    private int size;
    private int top1, top2;

    private TwoStacksSingleArray(int n){
        size = n;
        arr = new int[n];
        top1 = -1;
        top2 = size;
    }

    // Method to push an element x to stack1
    private void push1(int x) {
        // There is at least one empty space for new element
        if (top1 < top2 - 1){
            top1++;
            arr[top1] = x;
        } else{
            System.out.println("MyStack Overflow");
        }
    }

    // Method to push an element x to stack2
    private void push2(int x)
   {
       // There is at least one empty space for new element
       if (top1 < top2 - 1) {
           top2--;
           arr[top2] = x;
       } else{
           System.out.println("MyStack Overflow");
       }
   }

   // Method to pop an element from first stackqueue
   private int pop1()
   {
        if (top1 >= 0 )
        {
            int x = arr[top1];
            top1--;
            return x;
        } else{
            System.out.println("MyStack UnderFlow");
            return -1;
        }
   }

   // Method to pop an element from second stackqueue
   private int pop2()
   {
        if (top2 < size) {
            int x = arr[top2];
            top2++;
            return x;
        } else{
            System.out.println("MyStack UnderFlow");
            return -1;
        }
   }

    public static void main(String[] args) {
        TwoStacksSingleArray ts = new TwoStacksSingleArray(5);
        ts.push1(5);
        ts.push2(10);
        ts.push2(15);
        ts.push1(11);
        ts.push2(7);
        System.out.println("Popped element from stack1 is " + ts.pop1());
        ts.push2(40);
        System.out.println("Popped element from stack2 is " + ts.pop2());
    }
}

