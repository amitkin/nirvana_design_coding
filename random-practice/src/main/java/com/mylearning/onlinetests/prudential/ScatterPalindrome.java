package com.mylearning.onlinetests.prudential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScatterPalindrome {

    //https://stackoverflow.com/questions/57515296/return-count-of-scatter-palindrome-of-a-string
    public static List<Integer> scatterPalindrome(List<String> strToEvaluate) {
        List<Integer> result  = new ArrayList<>();
        if(strToEvaluate == null || strToEvaluate.size() == 0){
            return result;
        }

        for(String string : strToEvaluate){
            result.add(numerOfScatterPalindromes(string));
        }

        return result;
    }

    private static int numerOfScatterPalindromes(String string) {
        int answer = 0;
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0,1);
        int x = 0;
        for (char c : string.toCharArray()) {
            int d = c - 'a';
            x ^= 1 << d;
            answer += m.getOrDefault(x,0);
            for (int i = 0; i < 26; ++i) {
                answer += m.getOrDefault(x ^ (1 << i),0);
            }
            m.put(x, m.getOrDefault(x, 0)+1);
        }
        return answer;
    }

    private static int numerOfScatterPalindromes1(String s){
        int answer = 0;
        int n = s.length();

        int[] A = new int[n];

        for(int i = 0; i<n; i++) {
            A[i] = 0;
        }

        for(int i = 1; i<n; i++) {
            A[i]= A[i-1] ^ (1<<(s.charAt(i-1) - 'a'));
        }

        for(int i = 1; i<n; i++) {
            for(int j = 1; i<n; i++) {
                int x = A[j]^A[i-1];
                if ((x&(x-1)) == 0)    //if x is a power of 2 or not
                    answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        List<Integer> result = ScatterPalindrome.scatterPalindrome(Arrays.asList("mmwxwxeeyuhyklyuphyknlbipbqnwbflinbqhxnzowwiflnzhoxbcjnszowdiizrnobbpcujsdirqnbpudqqczjvdtplqcqzjxzjohcvtkvpofnylqjxpzpqoyhbeackqvqgjofnypsufpkqqaybeaqqgjejsyufnkxluqmadvjsqmzqeynxalutmgdvsqbmzqartogrgkbsrogtrmxkswtedmyxwedycvvheuucvvayhheuuattyphzgtaatpzgafrafkjrcnkjcnzzyyhfahdfadyhnyhsxnsgwxgwwwjbkjbukbubvvmmzzwfnwfhnhsstatuidtoltymahtuuidtolytqimdwhutqidweeqqieietttotuocktuczktzssnnddymymvvuittuvbichsvttwcvbecmkrhoascvkswcveytjmvkwmiugrohacksvoyrixtjxvjfwbjqmrjuiugkhjsoozrixoxjfbrjcqrjuskjwegrsokzorcjswgegraknvqjwgabsrxnvqrwbksrxerkunmyetunmyuytuybb"));
        result.stream().forEach(System.out::println);
    }
}
