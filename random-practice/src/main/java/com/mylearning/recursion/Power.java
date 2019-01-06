package com.mylearning.recursion;

public class Power {

    int power(int base, int exp){
        //base case
        if(exp == 0){
            return 1;
        } else{
            //breaking the problem into smaller one
            return base * power(base, exp - 1);
        }
    }

    public static void main(String[] args) {
        Power p = new Power();
        System.out.println(p.power(2, 3));
    }
}
