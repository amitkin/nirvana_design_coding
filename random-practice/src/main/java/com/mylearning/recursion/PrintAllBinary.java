package com.mylearning.recursion;

public class PrintAllBinary {

    void printAllBinary(int digits){
        printAllBinaryHelper(digits, "");
    }

    // Recursion
    // It doesn't work and I don't why?
    // It works and I don't know why?
    void printAllBinaryHelper(int digits, String prefix){
        // printBinary(3, "")
        //      printBinary(2, "0")
        //          printBinary(1, "00")
        //              printBinary(0, "000")
        //              printBinary(0, "001")
        //          printBinary(1, "01")
        //      printBinary(2, "1")
        //System.out.println("printBinary(" + digits + ", \"" + prefix + "\")");
        if(digits == 0){
            System.out.println(prefix);
        } else{
            printAllBinaryHelper(digits - 1, prefix + "0");
            printAllBinaryHelper(digits - 1, prefix + "1");
        }
    }

    public static void main(String[] args) {
        PrintAllBinary print = new PrintAllBinary();
        print.printAllBinary(3);
    }
}
