package com.mylearning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Practice {

    static int gcd(int a, int b)
    {
        if(a < 0)
            a = -a;
        if(b < 0)
            b = -b;
        if(b == 0)
            return a;

        if(a >= b && b > 0)
            return gcd(b,a%b);
        else
            return gcd(b, a);
    }
    // method to calculate all common divisors
    // of two given numbers
    // a, b --> input integer numbers
    static int commDiv(int a,int b)
    {
        // find gcd of a,b
        int n = gcd(a, b);

        // Count divisors of n.
        int result = 0;
        for (int i=1; i<=Math.sqrt(n); i++)
        {
            // if 'i' is factor of n
            if (n%i==0)
            {
                // check if divisors are equal
                if (n/i == i)
                    result += 1;
                else
                    result += 2;
            }
        }
        return result;
    }

    private static int countBuildings(int arr[]){
        int count = 0;
        int currMax = Integer.MIN_VALUE;
        for(int i=0; i< arr.length; i++){
            if(arr[i] > currMax) {
                count = count + 1;
                currMax = arr[i];
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception{
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        String[] input = name.split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        System.out.println(gcd(a, b));*/
        int[] nums = {1,3,6,7,9,4,10,5,6};
        System.out.println(countBuildings(nums));

    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < (dp[j] + 1)) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int maxans = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if(dp[i] > maxans);
            maxans = dp[i];
        }
        return maxans;
    }
}
