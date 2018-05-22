package com.mylearning.leetcode.string;

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

    private static String longestPalindrome(String s){
        if(s == null)
            return s;

        int length = s.length();
        if(s.length() < 2)
            return s;

        boolean[][] isPalindrome = new boolean[length][length];

        int left = 0;
        int right = 0;

        for(int j = 1; j < length; j++){
            for(int i = 0; i < j ; i++){
                boolean isInnerWordPalindrome = isPalindrome[i+1][j-1] || j - i <= 2;
                if(s.charAt(i) == s.charAt(j) && isInnerWordPalindrome){
                    isPalindrome[i][j] = true;

                    if(j - i > right - left){
                        left = i;
                        right = j;
                    }
                }
            }
        }
        return s.substring(left,right + 1);
    }

    public static void main(String[] args) {
        String str = ")()())";
        System.out.println("Length of longest valid parenthesis : " + longestValidParentheses(str));
        //System.out.println("Longest palindrome is : " + longestPalindrome(str));
    }
}
