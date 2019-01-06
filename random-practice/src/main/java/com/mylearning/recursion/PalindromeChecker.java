package com.mylearning.recursion;

public class PalindromeChecker {

    boolean isPalindrome(String s){
        s = s.toLowerCase();
        //base case
        if(s.length() < 2){
            return true;
        } else {
            //breaking the problem into smaller one
            if(s.charAt(0) == s.charAt(s.length() - 1)){
                return isPalindrome(s.substring(1, s.length() -1));
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        PalindromeChecker palindromeChecker = new PalindromeChecker();
        System.out.println(palindromeChecker.isPalindrome("abrakarba"));
    }
}
