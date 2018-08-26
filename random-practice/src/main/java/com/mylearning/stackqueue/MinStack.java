package com.mylearning.stackqueue;

import java.util.Stack;

/*
Push(x) : Inserts x at the top of stack.
- If stack is empty, insert x into the stack and make minElement equal to x.
- If stack is not empty, compare x with minElement. Two cases arise:
    - If x is greater than or equal to minElement, simply insert x.
    - If x is less than minElement, insert (2*x – minElement) into the stack and make minElement equal to x.
      For example, let previous minElement was 3. Now we want to insert 2. We update minElement as 2 and insert 2*2 – 3 = 1 into the stack.

Pop() : Removes an element from top of stack.
- Remove element from top. Let the removed element be y. Two cases arise:
    - If y is greater than or equal to minElement, the minimum element in the stack is still minElement.
    - If y is less than minElement, the minimum element now becomes (2*minElement – y), so update (minElement = 2*minElement – y).
      This is where we retrieve previous minimum from current minimum and its value in stack.
      For example, let the element to be removed be 1 and minElement be 2. We remove 1 and update minElement as 2*2 – 1 = 3.

Stack doesn’t hold actual value of an element if it is minimum so far.
Actual minimum element is always stored in minEle
*/

public class MinStack {
    private Stack<Integer> stack;
    private Integer minElement;

    private MinStack(){
        stack = new Stack<>();
    }

    int getMin(){
        if(stack.isEmpty()){
            System.out.println("Stack is empty!!");
            return -1;
        } else{
            return minElement;
        }
    }

    void push(int n){
        if(stack.isEmpty()){
            minElement = n;
            stack.push(n);
        } else{
            if(minElement > n){
                stack.push(2*n - minElement);
                minElement = n;
            } else{
                stack.push(n);
            }
        }

    }

    int pop(){
        if(stack.isEmpty()){
            System.out.println("Stack is empty!!");
            return -1;
        } else{
            int top = stack.pop();
            if(top < minElement){
                minElement = 2*minElement - top;
            }
            return top;
        }
    }

    int peek(){
        if(stack.isEmpty()){
            System.out.println("Stack is empty!!");
            return -1;
        } else{
            int top = stack.peek();
            return (top < minElement)? minElement: top;
        }
    }

    @Override
    public String toString() {
        return "MinStack{" +
                "stack=" + stack +
                ", minElement=" + minElement +
                ", top=" + peek() +
                '}';
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(5);
        minStack.push(3);
        minStack.push(4);
        minStack.push(1);
        minStack.push(6);
        minStack.pop();
        System.out.println(minStack);
        minStack.pop();
        System.out.println(minStack);
    }
}
