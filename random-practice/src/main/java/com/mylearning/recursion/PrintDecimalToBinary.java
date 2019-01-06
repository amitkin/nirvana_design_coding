package com.mylearning.recursion;

// https://www.youtube.com/watch?v=aOuNpEglzlU&list=PL_MAtxUzuk68qQNUOTzPWVAdzySpPrsEM&index=7
public class PrintDecimalToBinary {

    void printDecimalToBinary(int n){
        System.out.println("  printDecimalToBinary(" + n + ")");

        // binary of 43
        // binary of 21 (43/2)
        //      binary of 10
        //          binary of 5
        //              binary of 2
        //                  binary of 1
        //                  binary of 0
        //              binary of 1
        //          binary of 0
        //      binary of 1
        // + binary of 1 (43%2)
        if(n < 2){
            System.out.print(n);
        } else {
            int lastBinaryDigit = n % 2;
            int restOfBinaryDigit = n / 2;
            printDecimalToBinary(restOfBinaryDigit);
            System.out.print(lastBinaryDigit);
        }
    }

    public static void main(String[] args) {
        PrintDecimalToBinary p = new PrintDecimalToBinary();
        p.printDecimalToBinary(43);
    }
}
