package com.mylearning.stackqueue;

class CircularQueue
{
    final int[] a;
    int front, rear = -1, len = 0;

    private CircularQueue(int k) {
        a = new int[k];
    }

    private void enQueue(int element) {
        if (!isFull()) {
            rear = (rear + 1) % a.length;
            a[rear] = element;
            len++;
        } else {
            System.out.println("CircularQueue is full.");
        }
    }

    private int deQueue() {
        if (!isEmpty()) {
            int deleted = a[front];        // Delete the front element
            front = (front + 1) % a.length;
            len--;
            return deleted;
        } else {
            System.out.println("CircularQueue is empty.");
            return -1;
        }
    }

    public int Front() {
        return isEmpty() ? -1 : a[front];
    }

    public int Rear() {
        return isEmpty() ? -1 : a[rear];
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public boolean isFull() {
        return len == a.length;
    }

    private void display() {
        int curr = front;
        int noOfElements = len;
        System.out.print("CircularQueue state: ");
        if (len == 0) { System.out.print("[empty]"); }
        else while (noOfElements > 0) {
            System.out.print(a[curr] + " ");
            curr = (curr + 1) % a.length;
            noOfElements--;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(3);
        circularQueue.enQueue(1);  // return true
        circularQueue.enQueue(2);  // return true
        circularQueue.enQueue(3);  // return true
        circularQueue.enQueue(4);  // return false, the queue is full
        circularQueue.Rear();  // return 3
        circularQueue.isFull();  // return true
        circularQueue.deQueue();  // return true
        circularQueue.enQueue(4);  // return true
        circularQueue.Rear();  // return 4
        circularQueue.display();
    }
}
