package com.mylearning.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSet {

    ArrayList<Integer> nums;
    HashMap<Integer, Integer> locs;

    /** Initialize your data structure here. */
    public RandomSet() {
        nums = new ArrayList<>();
        locs = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = locs.containsKey(val);
        if ( contain ) return false;

        //Important to note here
        locs.put( val, nums.size()); //Keeping array index of val in map
        nums.add(val); //always inserting at end/appending is O(1), but insert at index is O(n) i.e. add(index, element)

        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = locs.containsKey(val);
        if ( ! contain ) return false;
        int loc = locs.get(val); //Got array index from map

        // not the last one than swap the last one with this val
        if (loc < nums.size() - 1 ) {
            int lastone = nums.get(nums.size() - 1 );
            //copy last value in array to current and later delete last one
            //nums.set( loc , lastone );
            Collections.swap(nums, loc, nums.size() - 1);
            //update the array index in map for last value copied
            locs.put(lastone, loc);
        }

        //Always removing last element to improve performance
        locs.remove(val);

        //when you remove the last element of the list; i.e. index == nums.size() - 1.
        //That can be performed with zero copying so it is O(1)
        nums.remove(nums.size() - 1);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        //int at = rand.nextInt(nums.size());

        //Random is thread safe for use by multiple threads. But if multiple threads use the same instance
        //of Random, the same seed is shared by multiple threads. It leads to contention between multiple
        //threads and so to performance degradation. ThreadLocalRandom is solution to above problem.
        //ThreadLocalRandom has a Random instance per thread and safeguards against contention.
        //So, basically, using a random instance per thread allows you to stop synchronizing on the seed
        //which must be used by all threads.
        int at = ThreadLocalRandom.current().nextInt(nums.size()); //For concurrent usecase in multithreaded designs
        return nums.get(at);
    }
}
