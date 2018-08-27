package com.mylearning.parallelcomputing;

public class MyDeadlock {

    private String str1 = "Java";
    private String str2 = "UNIX";

    private Thread trd1 = new Thread("My Thread 1"){
        public void run(){
            while(true){
                synchronized(str1){
                    synchronized(str2){
                        System.out.println(str1 + str2);
                    }
                }
            }
        }
    };

    private Thread trd2 = new Thread("My Thread 2"){
        public void run(){
            while(true){
                synchronized(str2){
                    synchronized(str1){
                        System.out.println(str2 + str1);
                    }
                }
            }
        }
    };

    public static void main(String a[]){
        MyDeadlock myDeadlock = new MyDeadlock();
        myDeadlock.trd1.start();
        myDeadlock.trd2.start();
    }
}
