package com.mylearning.recursion;

public class PrintStar {

    /*void printStars(int n){
        for (int i = 1; i <= n; i++) {
            System.out.print("*");
        }
    }*/

    void printStars(int n){
        //base case
        if(n == 0){
            return;
        } else{
            //breaking the problem into smaller one
            printStars(n - 1);
            System.out.print("*");
        }
    }

    public static void main(String[] args) {
        PrintStar print = new PrintStar();
        print.printStars(3);
    }
}
