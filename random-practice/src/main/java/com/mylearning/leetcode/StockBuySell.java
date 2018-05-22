package com.mylearning.leetcode;

import static java.lang.Math.max;

import java.util.ArrayList;

class StockBuySell {
    // This function finds the buy sell schedule for maximum profit
    void stockBuySell(int a[])
    {
        int n = a.length;
        if(n == 0 || n == 1)
            return;

        //count of solution pairs
        int count = 0;

        ArrayList<Interval> intervals = new ArrayList<>();
        int i=0;
        while (i < n-1){
            //Find local minima - compare with next and increment if smaller
            //limit is n-2 since comparing with next element
            while((i < n-1) && (a[i+1] <= a[i]))
                i++;//increment i which is minima
            if(i == n-1)
                break;

            Interval interval = new Interval();
            //Got the minima and assign it and increment for maxima
            interval.buy = i++;

            //Find local maxima - compare with local minima and increment if greater
            //limit is n-1 since comparing with previous element
            while((i < n) && (a[i] >= a[i-1]))
                i++;

            interval.sell = i-1;
            intervals.add(interval);

            count++;
        }

        if(count == 0){
            System.out.println("No solution found!!");
        } else {
            int maxprofit=0;
            for (i = 0; i < count; i++) {
                System.out.println("Buy on day:" + intervals.get(i).buy + " Sell on day:" + intervals.get(i).sell);
                maxprofit += (a[intervals.get(i).sell] - a[intervals.get(i).buy]);
            }
            System.out.println("Max Profit : " + maxprofit);
        }
    }

    //Maximum difference between two elements
    private static int maxProfit(int[] a) {
        int n = a.length;
        if(n == 0 || n == 1)
            return 0;

        int maxprofit=0, minsofar=a[0];
        for(int i=1;i<n;i++){
            if(a[i]-minsofar > maxprofit)
                maxprofit = a[i]-minsofar;
            if(a[i] < minsofar)
                minsofar = a[i];
        }
        return maxprofit;
    }

    private int max2TimesProfit(int[] prices) {
        int n = prices.length;
        if (n < 2)
            return 0;

        int buy[] = new int[2];
        buy[0] = buy[1] = Integer.MIN_VALUE;
        int sell[] = new int[2];
        sell[0] = sell[1] = 0;

        for (int price : prices) {
            sell[1] = max(sell[1], buy[1] + price);
            buy[1] = max(buy[1], sell[0] - price);
            sell[0] = max(sell[0], buy[0] + price);
            buy[0] = max(buy[0], 0 - price);
        }
        return sell[1];
    }

    public static void main(String[] args) {
        int[] a={3,5,9,1,4,3,8};
        int[] b={100, 180, 260, 310, 40, 535, 695};
        //System.out.println(maxProfit(a));//7
        StockBuySell s = new StockBuySell();
        //s.stockBuySell(a);
        int[] c={1,2,3,4};
        System.out.println(s.max2TimesProfit(c));
    }

    class Interval{
        int buy;
        int sell;
    }
}
