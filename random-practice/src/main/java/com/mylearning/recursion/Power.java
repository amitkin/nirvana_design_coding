package com.mylearning.recursion;

public class Power {

    //Time : O(n)
    int powerLinear(int base, int exp){
        //base case
        if(exp == 0){
            return 1;
        } else{
            //breaking the problem into smaller one
            return base * power(base, exp - 1);
        }
    }

    //Time : O(log n)
    int power(int base, int exp){
        //base case
        if(exp == 0){
            return 1;
        } else {
            int r = power(base, exp/2);
            if(exp%2 == 0) {
                return r * r;
            } else {
                return base * r * r;
            }
        }
    }

    public static void main(String[] args) {
        Power p = new Power();
        System.out.println(p.power(3, 3));
    }
}
