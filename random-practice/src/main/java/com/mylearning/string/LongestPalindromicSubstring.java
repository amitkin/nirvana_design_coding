package com.mylearning.string;

import java.util.Stack;

public class LongestPalindromicSubstring {

    public static int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
    public static int longestValidParenthesesWithoutSpace(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    private static int maxLen, lo;

    private static String longestPalindrome(String s){
        if(s == null)
            return s;

        int length = s.length();
        if(length < 2)
            return s;

        for (int i = 0; i < length - 1; i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i+1);
        }
        return s.substring(lo, lo + maxLen);
    }

    private static void extendPalindrome(String s, int i, int j){
        while(i>=0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        if(maxLen < j-i-1){
            lo = i+1;
            maxLen = j-i-1;
        }
    }

    public static void main(String[] args) {
        String str = "(()))(())";
        System.out.println("Length of longest valid parenthesis : " + longestValidParentheses(str));
        System.out.println("Longest palindrome is : " + longestPalindrome(str));
    }
}
