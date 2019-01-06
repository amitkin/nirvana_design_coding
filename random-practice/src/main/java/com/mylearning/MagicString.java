package com.mylearning;

import java.util.*;
/*
Asked in Visa hackerrank

Julia calls a string magical if the following conditions are satisfied:
Each letter ∈ {a, e, i, o, u}.
"a" must only be followed by "e".
"e" must only be followed by "a" or "i".
"i" must only be followed by "a", "e", "o", or "u".
"o" must only be followed by "i" or "u".
"u" must only be followed by "a".

Complete the magicalStrings function in your editor. It has 1 parameter: an integer, n, denoting the desired length of a magical string. It must return an integer denoting the number of magical strings of length n, modulo (109 + 7).

Input Format
The locked stub code in your editor reads the following input from stdin and passes it to your function:
The first line contains an integer, n, denoting the desired length of a magical string.

Constraints
0 < n < 105

Output Format
Your function must return an integer denoting the number of magical strings of length n, modulo (109 + 7). This is printed to stdout by the locked stub code in your editor.

Sample Input 0
1

Sample Output 0
5

Sample Input 1
2

Sample Output 1
10

Sample Input 2
3

Sample Output 2
19

Explanation
Sample Case 0:
There 5 magical strings: {"a", "e", "i", "o", "u"}.

Sample Case 1:
There 10 magical strings: {"ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou", "ua"}.

Sample Case 2:
There 19 magical strings: {"aea", "aei", "eae", "eia", "eie", "eio", "eiu", "iae", "iea", "iei", "ioi", "iou", "iua", "oia", "oie", "oio", "oiu", "oua", "uae"}.
 -}

-- "a" must only be followed by "e".
-- "e" must only be followed by "a" or "i".
-- "i" must only be followed by "a", "e", "o", or "u".
-- "o" must only be followed by "i" or "u".
-- "u" must only be followed by "a".
*/
public class MagicString{

    public static int magicalStrings(int n) {
        HashMap<Character, List<Character>> map = new HashMap<>();

        map.put('a',new ArrayList<Character>(){{add('e');}});
        map.put('e',new ArrayList<Character>(){{add('a');add('i');}});
        map.put('i',new ArrayList<Character>(){{add('a');add('e');add('o');add('u');}});
        map.put('o',new ArrayList<Character>(){{add('i');add('u');}});
        map.put('u',new ArrayList<Character>(){{add('a');}});

        if( n == 1){
            return map.size();
        }

        Set<Character> vowels = map.keySet();
        int result = 0;
        for(Character c : vowels){
            result += bfs(c,n,map);
        }
        return result;
    }

    //Inefficient since consumes a lot of memory by holding items in queue
    private static int bfs(Character c, int n, HashMap<Character, List<Character>> map){

        Queue<Character> queue = new LinkedList<>();
        queue.offer(c);
        queue.offer(null);

        int level = 0;
        int count = 0;
        while(!queue.isEmpty()){
            Character current = queue.poll();
            if(level == n-1) {
                count += queue.size();
                return count;
            }
            if(current != null){
                // get neighbours
                for(Character nei: map.get(current)){
                    queue.add(nei);
                }
            }else{
                //System.out.println(queue);
                if(!queue.isEmpty()){
                    queue.offer(null);
                }
                level++;
            }
        }

        return count;
    }

    private static int JustRecurse(int n){
         /*
         If you were to visit each node in the tree, either by bfs or dfs
         time complexity will be very high. Let us try to count the possibilities
         without actually going through each of them

         The inspiration is a tree structure only, the tree with height n has leaf nodes
         of all possible magical strings of length n.
         If the vowel must follow vowel condition wasn't there, we know that if you have F(n)
         possible strings of length n, you just need to multiply 5*F(n) to get F(n+1).

         The only thing determining number of branches each leaf node of tree gets is which
         specific vowel it is. So let us keep a track of the ending vowels of each F(n) separately.

         F(n) = total number of magical strings of length n

         Let us count the number of words that end with a, e, i, o, u
         Lets call X(n) the number of words of length N ending with x
         So A(n) is the number of words of length N ending with a

         Here, F(n) = A(n) + E(n) + I(n) + O(n) + U(n)

         If n = 1, it is easy,
         A(n) = E(n) = I(n) = O(n) = U(n) = 1

         What happens now?
         To find the number of words of length N = 2 ending with a, look at all the vowels
         that can be followed by a. We have "e", "i" and "u".
         So total number of words of length N ending with "a" are
         total number of words of length N - 1 ending with "e" , "i", or "u"

         So, we have


         A(n) = E(n-1) + I(n-1) + U(n-1)
         E(n) = A(n-1) + I(n-1)
         I(n) = E(n-1) + O(n-1)
         O(n) = I(n-1)
         U(n) = I(n-1) + O(n-1)


         Let's use this recurrence relation to loop and count the numbers
         Let's call A(n-1) = Am and A(n) = An

         Let's start with n-1 (denoted as m) = 1

         */

        int Am = 1, Em = 1, Im = 1, Om = 1,Um = 1;
        //Also initialising Xn with same values
        int An = 1, En = 1, In = 1, On = 1, Un = 1;
        //We will try to maintain the invariant Xm = Xn after each loop

        //We have manually initialised the variables such that N = 1 is handled,
        //now if N > 1
        for(int i=1; i<n; i++) {
            An = (Em + Im + Um)%(1000000007);
            En = (Am + Im)%(1000000007);
            In = (Em + Om)%(1000000007);
            On = Im;
            Un = (Im + Om)%(1000000007);

            Am = An;
            Em = En;
            Im = In;
            Om = On;
            Um = Un;
        }

        return (An + En + In + On + Un)%(1000000007);

    }

    public static void main(String [] args){
        System.out.println(magicalStrings(2));
        System.out.println(JustRecurse(2));
    }
}
