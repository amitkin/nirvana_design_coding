package com.mylearning.leetcode;

import java.io.*;

class CircularQ
{
    private int cQueue[];
    private int front;
    private int rear;
    private int count;
    private CircularQ(int count)
    {
        cQueue = new int[count];
        front = rear = this.count = 0;
    }

    private void enqueue(int element) {
        if(count == cQueue.length)
            System.out.println("Queue is full.");
        else{
            cQueue[rear] = element;
            rear = (rear + 1)%cQueue.length;
            count = count + 1;
        }
    }

    private int dequeue() {
        if(count == 0) {
            System.out.println("Queue is empty.");
            return -1;
        }
        else {
            int deleted = cQueue[front];        // Delete the front element
            cQueue[front] = 0;
            front = (front + 1)%cQueue.length;
            count = count - 1;
            return deleted;
        }
    }

    private void display() {
        int curr = front;
        int noOfElements = count;
        System.out.print("Queue state: ");
        if (count == 0) { System.out.print("[empty]"); }
        else while (noOfElements > 0) {
            System.out.print(cQueue[curr] + " ");
            curr = (curr + 1) % cQueue.length;
            noOfElements--;
        }
        System.out.println();
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the count of the queue : ");
        int size = Integer.parseInt(br.readLine());
        CircularQ call = new CircularQ(size);
        int choice;
        boolean exit = false;
        while(!exit)
        {
            System.out.print("\n1 : Add\n2 : Delete\n3 : Display\n4 : Exit\n\nYour Choice : ");
            choice = Integer.parseInt(br.readLine());
            switch(choice)
            {
            case 1 :
                System.out.print("\nEnter number to be added : ");
                int num = Integer.parseInt(br.readLine());
                call.enqueue(num);
                break;
            case 2 :
                int popped = call.dequeue();
                if(popped != -9999)
                    System.out.println("\nDeleted : " +popped);
                else
                    System.out.println("\nQueue is empty !");
                break;
            case 3 :
                call.display();
                break;
            case 4 :
                exit = true;
                break;
            default :
                System.out.println("\nWrong Choice !");
                break;
            }
        }
    }
}
