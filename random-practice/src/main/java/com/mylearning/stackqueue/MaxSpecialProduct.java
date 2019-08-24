package com.mylearning.stackqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class MaxSpecialProduct {

    /*
    Things to watch out for:
    1. Whether to use increasing or decreasing queue/stack
        increasing queue: find the first element smaller than current either in the left (from pushing in) or in the right (from popping out)
        decreasing queue: find the first element larger than current either in the left (from pushing in) or in the right (from popping out)
    2. Whether to use LinkedList or ArrayList for storing the indexes
    */
    public int maxSpecialProduct(ArrayList<Integer> A) {
        List<Integer> l = leftSpecial(A);
        List<Integer> r = rightSpecial(A);

        System.out.println(l); //[0, 0, 1, 1, 3, 4, 3, 0, 7, 8, 0]
        System.out.println(r); //[1, 0, 3, 7, 7, 6, 7, 0, 10, 10, 0]

        long product=0, res= 0;

        for(int i=0;i<A.size();i++){
            res= (long)l.get(i) * (long)r.get(i);
            if(product < res){
                product  = res;
            }
        }

        return (int)(product%1000000007);
    }

    private List<Integer> leftSpecial(ArrayList<Integer> A) {
        List<Integer> leftSpecial = new ArrayList<>();

        Deque<Integer> stack = new ArrayDeque<>();
        leftSpecial.add(0);
        stack.offerLast(0);
        for (int i = 1; i < A.size(); i++) {

            //pop elements till we get greater element than current
            while (!stack.isEmpty() && A.get(stack.peekLast()) <= A.get(i)) {
                stack.pollLast();
            }

            if (!stack.isEmpty()) {
                leftSpecial.add(stack.peekLast());
            } else {
                leftSpecial.add(0);
            }
            stack.offerLast(i);
        }
        return leftSpecial;
    }

    private List<Integer> rightSpecial(ArrayList<Integer> A){
        List<Integer> rightSpecial = new ArrayList<>();

        Deque<Integer> stack = new ArrayDeque<>();
        rightSpecial.add(0);
        stack.offerLast(A.size()-1);
        for (int i = A.size()-2; i >= 0; i--) {

            //pop elements till we get greater element than current
            while (!stack.isEmpty() && A.get(stack.peekLast()) <= A.get(i)) {
                stack.pollLast();
            }

            if (!stack.isEmpty()) {
                rightSpecial.add(0, stack.peekLast());
            } else {
                rightSpecial.add(0, 0);
            }

            stack.offerLast(i);
        }
        return rightSpecial;
    }

    public static void main(String[] args) {
        MaxSpecialProduct m = new MaxSpecialProduct();
        System.out.println(m.maxSpecialProduct(new ArrayList<>(Arrays.asList(5, 9, 6, 8, 6, 4, 6, 9, 5, 4, 9))));
    }
}
