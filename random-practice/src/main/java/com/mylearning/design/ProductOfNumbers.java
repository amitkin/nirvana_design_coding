package com.mylearning.design;

import java.util.ArrayList;

public class ProductOfNumbers {

    /*
    If we meet 0, the product including this 0 will always be 0.
    We only need to record the prefix product after it.
    So I clear the A and reinitilise it as [1], where 1 is the neutral element of multiplication.

    Complexity : Time O(1), Space O(N)
    */
    ArrayList<Integer> A;
    public ProductOfNumbers() {
        add(0);
    }

    public void add(int a) {
        if (a > 0)
            A.add(A.get(A.size() - 1) * a);
        else {
            A = new ArrayList();
            A.add(1);
        }
    }

    public int getProduct(int k) {
        int n = A.size();
        return k < n ? A.get(n - 1) / A.get(n - k - 1)  : 0;
    }

    public static void main(String[] args) {
        ProductOfNumbers obj = new ProductOfNumbers();
        obj.add(3);
        obj.add(0);
        obj.add(2);
        int param_4 = obj.getProduct(2);
        obj.add(5);
        obj.add(4);
        int param_1 = obj.getProduct(2);
        int param_2 = obj.getProduct(3);
        int param_3 = obj.getProduct(4);
    }
}
