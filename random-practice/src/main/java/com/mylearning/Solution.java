package com.mylearning;

import static java.lang.Math.max;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Solution {

    private static final Logger logger = Logger.getLogger(Solution.class.getName());

    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0, match = 0, starIdx = -1;
        while (sIdx < s.length()) {
            // advancing both pointers
            if (pIdx < p.length() && (p.charAt(pIdx) == '?' || s.charAt(sIdx) == p.charAt(pIdx))) {
                sIdx++;
                pIdx++;
            }
            // * found, only advancing pattern pointer
            else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                match = sIdx;
                pIdx++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                match++;
                sIdx = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else
                return false;
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
            if (pattern[i - 1] == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
                    T[i][j] = T[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    T[i][j] = T[i][j - 2];
                    if (pattern[j - 2] == '.' || pattern[j - 2] == text[i - 1]) {
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[text.length][pattern.length];
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int counter = 0;
        int begin = 0, end = 0;
        int d = 0;

        for (char c : s.toCharArray())
            map.put(c, 0);

        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map.get(c1) == 0) {
                counter++;
            }
            map.put(c1, map.get(c1) + 1);
            end++;

            while (counter > 2) {
                char c2 = s.charAt(begin);
                if (map.get(c2) == 1) {
                    counter--;
                }
                map.put(c2, map.get(c2) - 1);
                begin++;
            }
            d = max(d, end - begin);
        }
        return d;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] map = new int[256];
        int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] == 0)
                counter++;
            map[c1]++;
            end++;

            while (counter > k) {
                final char c2 = s.charAt(start);
                if (map[c2] == 1)
                    counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }

    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray())
            map.put(c, 0);

        for (char c : t.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return "";
        }

        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map.get(c1) > 0)
                counter--;
            map.put(c1, map.get(c1) - 1);
            end++;

            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }

                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);
                if (map.get(c2) > 0)
                    counter++;
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
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
        if (k >= nums.length)
            k = k - nums.length;
        rightRotate(nums, k, nums.length);
    }

    public void rightRotate(int[] nums, int k, int n) {
        if (k >= nums.length)
            k = k - nums.length;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // function to match nuts and bolts
    private void nutboltmatch(char nuts[], char bolts[], int n) {
        HashMap<Character, Integer> hash = new HashMap<>();

        // creating a hashmap for nuts
        for (int i = 0; i < n; i++)
            hash.put(nuts[i], i);

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

    private void quickSort(int[] arr, int low, int high) {
        if (low >= high)
            return;
        int partionIndex = partition(arr, low, high, arr[high]);
        quickSort(arr, low, partionIndex - 1);
        quickSort(arr, partionIndex + 1, high);
    }

    private int partition(int[] arr, int low, int high, int pivot) {
        int i = low;
        int temp;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                //swap i and j
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            } else if (arr[j] == pivot) {
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

    private int[] union(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;
        boolean flag1 = false;        // flag to check if anything has been considered from array1 in union
        boolean flag2 = false;        // flag to check if anything has been considered from array2 in union

        ArrayList<Integer> list = new ArrayList<>();
        while ((i < m) && (j < n)) {
            if (nums1[i] == nums2[j]) {
                if (!flag1 || !flag2) {
                    list.add(nums1[i]);
                    flag1 = true;
                    flag2 = true;
                } else {
                    if (nums1[i] != nums1[i - 1])
                        list.add(nums1[i]);
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                if (!flag1) {
                    list.add(nums1[i]);
                    flag1 = true;
                } else {
                    if (nums1[i] != nums1[i - 1])
                        list.add(nums1[i]);
                }
                i++;
            } else {
                if (!flag2) {
                    list.add(nums2[j]);
                    flag2 = true;
                } else {
                    if (nums2[j] != nums2[j - 1])
                        list.add(nums2[j]);
                }
                j++;
            }
        }

        if (i == m) {
            while (j < n) {
                if (!flag2) {
                    list.add(nums2[j]);
                    flag2 = true;
                } else {
                    if (nums2[j] != nums2[j - 1])
                        list.add(nums2[j]);
                }
                j++;
            }
        }

        if (j == n) {
            while (i < m) {
                if (!flag1) {
                    list.add(nums1[i]);
                    flag1 = true;
                } else {
                    if (nums1[i] != nums1[i - 1])
                        list.add(nums1[i]);
                }
                i++;
            }
        }

        int[] result = new int[list.size()];
        int k = 0;
        for (int l : list) {
            result[k++] = l;
        }

        return result;
    }

    private int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;

        ArrayList<Integer> list = new ArrayList<>();

        //For duplicates
        while ((i < m) && (j < n)) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j])
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
        int k = 0;
        for (int l : list) {
            result[k++] = l;
        }

        return result;
    }

    private void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1)
            return;

        /*int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }*/

        for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                if (lastNonZeroFoundAt != cur)
                    swap(nums, lastNonZeroFoundAt, cur);
                lastNonZeroFoundAt++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        nums[a] = nums[a] + nums[b];
        nums[b] = nums[a] - nums[b];
        nums[a] = nums[a] - nums[b];
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int col = matrix[0].length - 1;  //matrix.length is no of rows, matrix[0].length is no.of columns
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    public static int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)
            return 0;

        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) {
                    result = result + 1;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    result = result + 1;
                }
            }
        }
        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resultIndex = 0;
        // store index
        // ArrayDeque is internally backed by circular array and uses less memory compared to LinkedList
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll(); //first inserted ones
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast(); //last inserted ones
            }
            // q contains index... result contains content
            q.offer(i);
            if (i >= k - 1) {
                result[resultIndex++] = nums[q.peek()];
            }
        }
        return result;
    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    void printNGE(int arr[], int n) {
        int next, i, j;
        for (i = 0; i < n; i++) {
            next = -1;
            for (j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    next = arr[j];
                    break;
                }
            }
        }
    }

    //3,3,3,1,1
    //1,2,3,4,5
    //5,4,3,2,1
    //3,4,5,6,1
    public static void finalPrice(List<Integer> prices) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.size(); i++) {
            while (!stack.isEmpty() && prices.get(i) <= prices.get(stack.peek())) {
                int prevPriceIndex = stack.pop();
                int discount = prices.get(i);
                map.put(prevPriceIndex, prices.get(prevPriceIndex) - discount);
            }
            stack.push(i);
        }

        List<String> nonDiscountIndexes = new ArrayList<>();
        int totalCost = 0;
        for (int i = 0; i < prices.size(); i++) {
            if (map.containsKey(i)) {
                totalCost += map.get(i);
            } else {
                totalCost += prices.get(i);
                nonDiscountIndexes.add(i + "");
            }
        }

        System.out.println(totalCost);
        System.out.println(String.join(" ", nonDiscountIndexes));
    }

    public static int solve(List<Integer> A, List<Integer> B, List<Integer> C) {
        int aLength = A.size();
        int bLength = B.size();
        int cLength = C.size();

        int maxLength =
                aLength > bLength ? (aLength > cLength ? aLength : cLength) : (bLength > cLength ? bLength : cLength);

        int i = 0, j = 0, k = 0;
        int currentMax = 0;
        int currentMin = 0;
        int minDifference = Integer.MAX_VALUE;

        while (i < maxLength) {

            if (A.get(i) < B.get(j) && A.get(i) < C.get(k)) {
                i++;
                currentMin = A.get(i);
                currentMax = B.get(j) < C.get(k) ? C.get(k) : B.get(j);
            } else if (B.get(j) <= A.get(i) && B.get(j) < C.get(k)) {
                j++;
                currentMin = B.get(j);
                currentMax = A.get(i) < C.get(k) ? C.get(k) : A.get(i);
            } else if (C.get(k) < A.get(i) && C.get(k) <= B.get(j)) {
                k++;
                currentMin = C.get(k);
                currentMax = A.get(i) < B.get(j) ? B.get(j) : A.get(i);
            }

            minDifference = Math.min(minDifference, Math.abs(currentMax - currentMin));

        }

        return minDifference;
    }

    public static void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        int i = a.size() - 1;
        int j = b.size() - 1;
        int k = a.size() + b.size() - 1;

        while (i >= 0 && j >= 0) {
            if (a.get(i) > b.get(j)) {
                a.add(k, a.get(i));
                i--;
                k--;
            } else {
                a.add(k, b.get(j));
                j--;
                k--;
            }
        }

        while (i >= 0) {
            a.add(k, a.get(i));
            i--;
            k--;
        }

        while (j >= 0) {
            a.add(k, b.get(j));
            j--;
            k--;
        }
    }

    class UF {
        int[] id;
        public UF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }
        //Find the representative recursively and does path compression as well
        public int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
        public boolean isConnected(int p, int q) {
            return root(p)==root(q);
        }
        public void union(int p, int q) {
            //if they are part of same set do nothing
            if (isConnected(p, q)) return;
            //else one of them's parent(q's parent) becomes parent of other(p's parent)
            id[root(p)] = root(q);
        }
    }

    public int swimInWater(int[][] grid) {
        int N = grid.length;
        UF uf = new UF(N*N);
        int time = 0;
        while(!uf.isConnected(0, N*N-1)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] > time) continue;
                    /*
                    At first i*N+j is a really common way to transform 2D matrix into 1D array.
                    Then, if (i < N-1 && grid[i+1][j]<=time) uf.union(iN+j, iN+j+N) is move to the next row,
                    for example, you are currently at grid[1][2] and you're moving to grid[2][2];
                    and for if (j < N-1 && grid[i][j+1]<=time) uf.union(iN+j, iN+j+1) is move to the next column,
                    for example, grid[1][2] to grid[1][3]
                    */
                    if (i < N-1 && grid[i+1][j]<=time) uf.union(i*N+j, i*N+j+N);
                    if (j < N-1 && grid[i][j+1]<=time) uf.union(i*N+j, i*N+j+1);
                }
            }
            time++;
        }
        return time - 1;
    }

    public int numUniqueEmails(String[] emails) {
        Set<String> seen = new HashSet();
        for (String email: emails) {
            String[] splits = email.split("@");
            String local = splits[0];
            String rest = splits[1];
            //int i = email.indexOf('@');
            //String local = email.substring(0, i);
            //String rest = email.substring(i);
            if (local.indexOf('+') > 0) {
                local = local.substring(0, local.indexOf('+'));
            }
            local = local.replaceAll("\\.", "");
            seen.add(local + "@" + rest);
        }

        return seen.size();
    }

    public boolean isHappy(int n) {
        while(true) {
            int result = 0;
            while(n > 0) {
                result = result + (n%10)*(n%10);
                n = n/10;
            }
            if(result == 1) {
                return true;
            } else {
                System.out.println(result);
                n = result;
            }
        }
    }

    static int[] pancakeSort(int[] arr) {
        // your code goes here

        int n = arr.length;

        for(int i=0; i<n; i++) {
            int curMaxIndex = getMaxIndex(arr, n - i);
            flip(arr, curMaxIndex + 1);
            flip(arr, n - i);
        }

        return arr;
    }

    private static void flip(int[] arr, int k) {
        if(k > 1 && k <= arr.length) {
            int start = 0, end = k - 1;
            while(start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }
    }

    private static int getMaxIndex(int[] arr, int end) {
        int maxIndex = 0;
        for(int i=1; i<end; i++) {
            if(arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    static public int trimArray(int[] nums, int k) {
        /*int n = nums.length;
        if (n <= k) return n;

        int i = 1, j = 1;
        int count = 1;
        while (j < n) {
            if (nums[j] != nums[j-1]) {
                count = 1;
                nums[i++] = nums[j];
            } else {
                if (count < k) {
                    nums[i++] = nums[j];
                    count++;
                }
            }
            ++j;
        }
        return i;*/

        int i=0, j=1;
        while(j < nums.length) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j++];
            }  else {
                j++;
            }
        }
        return i+1;
    }

    public void leftRotateArray(List<Integer> arr, Integer k){
        int n = arr.size();
        if(n<=1) return;

        int[] a = new int[n];
        /*if(k>0) {
            k = k%n;
            for(int i=0; i<n; i++) {
                a[(i-k+n)%n] = arr.get(i);
            }
            for(int i=0; i<n; i++) {
                arr.set(i,a[i]);
            }
        }*/
        for(int p=0; p<n; p++) {
            int i = (p+k)%n;
            a[p] = arr.get(i);
        }

        for(int i=0; i<n; i++) {
            arr.set(i,a[i]);
        }
    }

    private void leftRotateArray(int[] nums, int k) {
        int n = nums.length;
        if(n<=1) return;

        if (k>0) {
            k = k % n;
            reverse(nums, 0, n - 1);
            reverse(nums, 0, n - k - 1);
            reverse(nums, n - k, n - 1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        //int[] a = {1,2,2,2,2,3,3,4,4,4,4,4,5};
        //System.out.println(trimArray(a, 3)); //{1,2,2,2,3,3,4,4,4,5} = 10
        //int[] arr = {1,2,3,4,5};
        List<Integer> arrList = Arrays.asList(1,2,3,4,5);
        s.leftRotateArray(arrList, 7);
        //s.leftRotateArray(arr, 6);
        System.out.println(arrList);
        //pancakeSort(arr);
        //System.out.println(Arrays.toString(arr));

        //Solution s = new Solution();
        //System.out.println(s.isHappy(11));
        //System.out.println(s.numUniqueEmails(new String[]{"test.email+alex@leetcode.com","test.email.leet+alex@code.com"}));
        //System.out.println(s.solve(new ArrayList<>(Arrays.asList(1, 2, 4)), new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
        //System.out.println(s.solve(new ArrayList<>(Arrays.asList(39, 99, 70, 24, 49, 13, 86, 43, 88, 74, 45, 92, 72, 71, 90, 32, 19, 76, 84, 46, 63, 15, 87, 1, 39, 58, 17, 65, 99, 43, 83, 29, 64, 67, 100, 14, 17, 100, 81, 26, 45, 40, 95, 94, 86, 2, 89, 57, 52, 91, 45)), new ArrayList<>(Arrays.asList(1221, 360, 459, 651, 958, 584, 345, 181, 536, 116, 1310, 403, 669, 1044, 1281, 711, 222, 280, 1255, 257, 811, 409, 698, 74, 838))));

        //int[][] grid = {{0,2},{1,3}};
        //s.swimInWater(grid);
        /*int arr[] = { 1, 3, 1, 5, 4 };
        int k = 0;
        findPairs(arr, k);

        Solution s = new Solution();
        System.out.println(s.firstMissingPositive(new int[] { 7, 8, 9, 11, 12 }));

        int matrix[][] = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
                { 18, 21, 23, 26, 30 } };
        System.out.println("Searched 5 in matrix : " + s.searchMatrix(matrix, 9));

        String str = "aab";
        String pat = "c*a*b";

        System.out.println(s.matchRegex(str.toCharArray(), pat.toCharArray()));
        System.out.println(s.minWindow(str, pat));

        s.moveZeroes(arr);
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
        /*for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
        // Nuts and bolts are represented as array of characters
        char nuts[] = { '@', '#', '$', '%', '^', '&' };
        char bolts[] = { '$', '%', '&', '^', '@', '#' };

        // Method based on quick sort which matches nuts and bolts
        //s.nutboltmatch(nuts, bolts, 6);

        int a[] = { 1, 3, -1, -3, 5, 3, 6, 7 };
        System.out.println(Arrays.toString(maxSlidingWindow(a, 3)));

        logger.setLevel(Level.FINER);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.FINER);
        logger.addHandler(handler);
        logger.finer("ABC");*/
    }
}

