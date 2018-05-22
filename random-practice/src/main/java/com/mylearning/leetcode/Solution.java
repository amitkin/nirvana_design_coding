package com.mylearning.leetcode;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0, match = 0, starIdx = -1;
        while (sIdx < s.length()){
            // advancing both pointers
            if (pIdx < p.length()  && (p.charAt(pIdx) == '?' || s.charAt(sIdx) == p.charAt(pIdx))){
                sIdx++;
                pIdx++;
            }
            // * found, only advancing pattern pointer
            else if (pIdx < p.length() && p.charAt(pIdx) == '*'){
                starIdx = pIdx;
                match = sIdx;
                pIdx++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                pIdx = starIdx + 1;
                match++;
                sIdx = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (pIdx < p.length() && p.charAt(pIdx) == '*')
            pIdx++;

        return pIdx == p.length();
    }

    public boolean matchRegex(char[] text, char[] pattern) {
        boolean T[][] = new boolean[text.length + 1][pattern.length + 1];

        T[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) {
            if (pattern[i-1] == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j - 1] == '*')  {
                    T[i][j] = T[i][j - 2];
                    if (pattern[j-2] == '.' || pattern[j - 2] == text[i - 1]) {
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[text.length][pattern.length];
    }

    public int lengthOfLongestSubstring(String s){
        HashMap<Character,Integer> map = new HashMap<>();
        int counter=0;
        int begin=0, end=0;
        int d=0;

        for(char c : s.toCharArray())
            map.put(c, 0);

        while(end < s.length()){
            char c1 = s.charAt(end);
            if(map.get(c1) == 0){  counter++; }
            map.put(c1, map.get(c1)+1);
            end++;

            while(counter > 2){
                char c2 = s.charAt(begin);
                if(map.get(c2) == 1){ counter--; }
                map.put(c2, map.get(c2)-1);
                begin++;
            }
            d=max(d, end-begin);
        }
        return d;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] map = new int[256];
        int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] == 0) counter++;
            map[c1]++;
            end++;

            while (counter > k) {
                final char c2 = s.charAt(start);
                if (map[c2] == 1) counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }

    public String minWindow(String s, String t) {
        HashMap<Character,Integer> map = new HashMap<>();

        for(char c : s.toCharArray())
            map.put(c, 0);

        for(char c : t.toCharArray())
        {
            if(map.containsKey(c))
                map.put(c, map.get(c)+1);
            else
                return "";
        }

        int start=0, end=0, minStart=0, minLen = Integer.MAX_VALUE, counter = t.length();
        while(end < s.length())
        {
            char c1 = s.charAt(end);
            if(map.get(c1) > 0)
                counter--;
            map.put(c1,map.get(c1)-1);
            end++;

            while(counter == 0)
            {
                if(minLen > end-start)
                {
                    minLen = end-start;
                    minStart = start;
                }

                char c2 = s.charAt(start);
                map.put(c2, map.get(c2)+1);
                if(map.get(c2) > 0)
                    counter++;
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart,minStart+minLen);
    }

    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num))
                return true;
            else
                hashMap.put(num, 1);
        }
        return false;
    }

    private void rotate(int[] nums, int k) {
        if(k >= nums.length)
            k = k - nums.length;
        rightRotate(nums, k, nums.length);
    }

    private void rightRotate(int[] nums, int k, int n) {
        reverse(nums, 0, n-k-1);
        reverse(nums, n-k, n-1);
        reverse(nums, 0, n-1);
    }

    private void reverse(int[] nums, int start, int end){
        int temp;
        while(start < end){
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // function to match nuts and bolts
    private void nutboltmatch(char nuts[], char bolts[], int n)
    {
        HashMap<Character, Integer> hash = new HashMap<>();

        // creating a hashmap for nuts
        for (int i = 0; i < n; i++)
            hash.put(nuts[i],i);

        // searching for nuts for each bolt in hash map
        for (int i = 0; i < n; i++)
            if (hash.containsKey(bolts[i]))
                nuts[i] = bolts[i];

        // print the result
        System.out.println("matched nuts and bolts are-");
        for (int i = 0; i < n; i++)
            System.out.print(nuts[i] + " ");
        System.out.println("");
        for (int i = 0; i < n; i++)
            System.out.print(bolts[i] + " ");
    }

    private void quickSort(int[] arr, int low, int high){
        if(low >= high)
            return;
        int partionIndex = partition(arr, low, high, arr[high]);
        quickSort(arr, low, partionIndex-1);
        quickSort(arr, partionIndex+1, high);
    }

    private int partition(int[] arr, int low, int high, int pivot){
        int i = low;
        int temp;
        for(int j = low; j < high; j++){
            if(arr[j] < pivot){
                //swap i and j
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }else if(arr[j] == pivot){
                //swap j and high
                temp = arr[j];
                arr[j] = arr[high];
                arr[high] = temp;
                j--;
            }
        }
        //swap high(pivot) and i to get the partition
        temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    private int[] union(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        int i=0,j=0;
        boolean flag1 = false;		// flag to check if anything has been considered from array1 in union
        boolean flag2 = false;		// flag to check if anything has been considered from array2 in union

        ArrayList<Integer> list = new ArrayList<>();
        while((i<m) && (j<n)) {
            if(nums1[i] == nums2[j]) {
                if(!flag1 || !flag2) {
                    list.add(nums1[i]);
                    flag1 = true;
                    flag2 = true;
                }
                else {
                    if(nums1[i] != nums1[i-1])
                        list.add(nums1[i]);
                }
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j]) {
                if(!flag1) {
                    list.add(nums1[i]);
                    flag1 = true;
                }
                else {
                    if(nums1[i] != nums1[i-1])
                        list.add(nums1[i]);
                }
                i++;
            }
            else {
                if(!flag2) {
                    list.add(nums2[j]);
                    flag2 = true;
                }
                else {
                    if(nums2[j] != nums2[j-1])
                        list.add(nums2[j]);
                }
                j++;
            }
        }

        if(i == m) {
            while(j < n) {
                if(!flag2) {
                    list.add(nums2[j]);
                    flag2 = true;
                }
                else {
                    if(nums2[j] != nums2[j-1])
                        list.add(nums2[j]);
                }
                j++;
            }
        }

        if(j == n) {
            while(i < m) {
                if(!flag1) {
                    list.add(nums1[i]);
                    flag1 = true;
                }
                else {
                    if(nums1[i] != nums1[i-1])
                        list.add(nums1[i]);
                }
                i++;
            }
        }

        int[] result = new int[list.size()];
        int k=0;
        for(int l: list){
            result[k++] = l;
        }

        return result;
    }

    private int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int m = nums1.length;
        int n = nums2.length;
        int i=0,j=0;

        ArrayList<Integer> list = new ArrayList<>();

        //For duplicates
        while((i<m) && (j<n)) {
            if(nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j])
                i++;
            else
                j++;
        }

        //If all are distinct
        /*for(i=0; i<nums1.length; i++){
            if(i==0 || (i>0 && nums1[i]!=nums1[i-1])){
                if(Arrays.binarySearch(nums2, nums1[i])>-1){
                    list.add(nums1[i]);
                }
            }
        }*/

        int[] result = new int[list.size()];
        int k=0;
        for(int l: list){
            result[k++] = l;
        }

        return result;
    }

    private void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) return;

        /*int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }*/

        for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                if(lastNonZeroFoundAt != cur)
                    swap(nums, lastNonZeroFoundAt, cur);
                lastNonZeroFoundAt++;
            }
        }
    }

    private void swap(int[] nums, int a, int b){
        nums[a] = nums[a] + nums[b];
        nums[b] = nums[a] - nums[b];
        nums[a] = nums[a] - nums[b];
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;  //matrix.length is no of rows, matrix[0].length is no.of columns
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int matrix[][] = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println("Searched 5 in matrix : " + s.searchMatrix(matrix, 9));

        String str = "aab";
        String pat = "c*a*b";

        System.out.println(s.matchRegex(str.toCharArray(), pat.toCharArray()));
        //System.out.println(s.minWindow(str, pat));

        int[] arr = {1,0};
        //s.moveZeroes(arr);
        /*int arr[] = {8,7,1,2,3,4,6,5};

        int arr1[] = {1,2,2,1}, arr2[] = {2,3,4,2};
        int result[] = s.intersection(arr1, arr2);
        for (int r: result) {
            System.out.print(r + " ");
        }*/
        //System.out.println("Partition : " + s.partition(arr, 0, arr.length-1, arr[arr.length-1]));
        //s.quickSort(arr, 0, arr.length-1);
        //s.reverse(arr, 0, 2);
        //s.rotate(arr, 3);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
        // Nuts and bolts are represented as array of characters
        char nuts[] = {'@', '#', '$', '%', '^', '&'};
        char bolts[] = {'$', '%', '&', '^', '@', '#'};

        // Method based on quick sort which matches nuts and bolts
        //s.nutboltmatch(nuts, bolts, 6);

    }
}
