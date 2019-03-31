package com.mylearning.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* The idea of dividing existing numbers into several ranges:

   Say we already have 10k numbers in list,
   each time adding a number requires sorting all 10k numbers, which is slow.

   To optimize, we can store 10k numbers in several (say 10) lists,
   and nums in each list are sorted.

   Then each time we add a number, just need to find one list with correct range,
   insert the number and sort this list only. Since its size is relatively small, it's fast.

   Buckets have size O(sqrt(total_size)) and number of buckets is also O(sqrt(total_size)).
   Then addNum would be O(sqrt(total_size)) or O(sqrt(total_size) * log(total_size))

   References : https://leetcode.com/problems/find-median-from-data-stream/discuss/74057/Tired-of-TWO-HEAPSET-solutions-See-this-segment-dividing-solution-(c%2B%2B)

*/

public class MedianFinder {
    private LinkedList<LinkedList<Integer>> buckets; // store all ranges
    private int total_size;

    MedianFinder() {
        total_size = 0;
        buckets = new LinkedList<>();
        buckets.add(new LinkedList<>());
    }

    void addNum(int num) {
        List<Integer> correctRange = new LinkedList<>();
        int targetIndex = 0;

        // find the correct range to insert given num
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.size() == 1 ||
                    (i == 0 && num <= buckets.get(i).getLast()) ||
                    (i == buckets.size() - 1 && num >= buckets.get(i).getFirst()) ||
                    (buckets.get(i).getFirst() <= num && num <= buckets.get(i).getLast()) ||
                    (num > buckets.get(i).getLast() && num < buckets.get(i+1).getFirst())) {
                        correctRange = buckets.get(i);
                        targetIndex = i;
                        break;
            }
        }

        // put num at back of correct range, and sort it to keep increasing sequence
        total_size++;
        correctRange.add(num);

        //depending on how efficient sort is (could be linear, as it just needs to insert the new element into an already sorted list).
        Collections.sort(correctRange);

        // if currentRange's size > threshold, split it into two halves and add them back to buckets
        int len = correctRange.size();
        if (len * len > total_size) {
            LinkedList<Integer> half1 = new LinkedList<>(correctRange.subList(0, (len) / 2));
            LinkedList<Integer> half2 = new LinkedList<>(correctRange.subList((len) / 2, len));

            buckets.set(targetIndex, half1);
            buckets.add(targetIndex + 1, half2);
        }

    }

    // iterate thru all ranges in buckets to find median value
    double findMedian() {
        if (total_size==0)
            return 0;

        int mid1 = total_size/2;
        int mid2 = mid1 + 1;

        int leftCount=0;
        double first = 0.0, second = 0.0;
        for (List<Integer> bucket : buckets) {
            if (leftCount<mid1 && mid1<=leftCount+bucket.size())
                first = bucket.get(mid1 - leftCount - 1);

            if (leftCount<mid2 && mid2<=leftCount+bucket.size()) {
                second = bucket.get(mid2 - leftCount - 1);
                break;
            }
            leftCount += bucket.size();
        }

        if (total_size % 2 != 0)
            return second;
        else
            return (first + second)/2;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(3);
        medianFinder.addNum(1);
        medianFinder.addNum(5);
        medianFinder.addNum(2);
        medianFinder.addNum(8);
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
    }
}
