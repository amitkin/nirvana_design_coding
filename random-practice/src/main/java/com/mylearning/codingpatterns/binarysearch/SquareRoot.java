package com.mylearning.codingpatterns.binarysearch;

public class SquareRoot {
    public static float findSquareRoot(int number, int precision)
    {
        int start = 0, end = number;
        int mid;
        double ans = 0.0;

        while (start <= end) {
            mid = start + (end - start) / 2;
            if (mid * mid == number) {
                ans = mid;
                break;
            }
            if (mid * mid < number) {
                start = mid + 1;
                ans = mid;
            } else {
                end = mid - 1;
            }
        }

        // For computing the fractional part of square root upto given precision
        double increment = 0.1;
        for (int i = 0; i < precision; i++) {
            while (ans * ans <= number) {
                ans += increment;
            }
            // loop terminates when ans * ans > number
            ans = ans - increment;
            increment = increment / 10;
        }
        return (float)ans;
    }

    public static void main(String[] args) {
        System.out.println(findSquareRoot(5, 3));
    }
}
