package com.mylearning.recursion;

public class PrintDecimal {

    void printDecimal(int digits){
        printDecimalHelper(digits, "");
    }

    void printDecimalHelper(int digits, String prefix){
        System.out.println("printDecimalHelper(" + digits + ", \"" + prefix + "\")");
        if(digits == 0){
            System.out.println(prefix);
        } else{
            for (int i = 0; i <= 9; i++) {
                printDecimalHelper(digits - 1, prefix + Integer.toString(i));
            }
        }
    }

    public static void main(String[] args) {
        PrintDecimal print = new PrintDecimal();
        print.printDecimal(3);
    }
}
