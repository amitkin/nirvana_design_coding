package com.mylearning.genericstack;

import java.lang.reflect.Array;

public class StackArray<T> implements Stack<T> {
    private final int maxSize;
    private Object[] array;
    private int top;

    public StackArray(int maxSize) {
        this.maxSize = maxSize;
        this.array = new Object[maxSize];
        this.top = -1;
    }

    private T[] resizeArray() {
        //create a new array double the size of the old, copy the old elements then return the new array
        T[] newArray = (T[]) Array.newInstance(StackArray.class, maxSize * 2);
        for(int i = 0; i < maxSize; i++) {
            newArray[i] = (T)this.array[i];
        }
        return newArray;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize-1;
    }

    public void push(T element) {
        if(!this.isFull()) {
            ++top;
            array[top] = element;
        }
        else {
            this.array = resizeArray();
            array[++top] = element;
        }
    }

    public T pop() {
        if(this.isEmpty()) {
            System.out.println("Stack is empty!!");
        }
        return element(top--);
    }

    public T peek() {
        if(this.isEmpty()) {
            System.out.println("Stack is empty!!");
        }
        return element(top);
    }

    private T element(int index) {
        return (T)array[index];
    }
}
