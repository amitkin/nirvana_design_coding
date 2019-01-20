package com.mylearning.onlinetests.zalando;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

class CodilityTest {
    public String solution(int[] T) {
        int n = T.length / 4;

        // WINTER SPRING SUMMER AUTUMN
        List<Entry<Integer, String>> amplitudes =  new ArrayList<>();
        amplitudes.add(new AbstractMap.SimpleEntry<>(diff(T, 0, n), "WINTER"));
        amplitudes.add(new AbstractMap.SimpleEntry<>(diff(T, n, n * 2), "SPRING"));
        amplitudes.add(new AbstractMap.SimpleEntry<>(diff(T, n * 2, n * 3), "SUMMER"));
        amplitudes.add(new AbstractMap.SimpleEntry<>(diff(T, n * 3, n * 4), "AUTUMN"));
        amplitudes.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));
        return amplitudes.get(0).getValue();
    }

    private int diff(int[] T, int lo, int hi) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = lo; i < hi; ++i) {
            min = Math.min(min, T[i]);
            max = Math.max(max, T[i]);
        }
        return max - min;
    }

    public String solution(int A, int B){
        int hi ;
        int lo ;
        StringBuilder result = new StringBuilder();
        Character hiStr, loStr;

        if (A > B) {
            hi = A;
            hiStr = 'a';
            lo = B;
            loStr = 'b';
        } else {
            hi = B;
            hiStr = 'b';
            lo = A;
            loStr = 'a';
        }
        while(lo < hi && hi > 0 && lo > 0){
            result.append(hi > 2 ? getNCharacter(hiStr, 2) : getNCharacter(hiStr,hi));
            result.append(loStr);
            hi = hi-2;
            lo = lo-1;
        }

        while(hi > 0 && lo > 0){
            result.append(hiStr);
            result.append(loStr);
            hi--;
            lo--;
        }

        if(hi > 0){
            result.append(getNCharacter(hiStr,hi));
        }

        if(lo > 0){
            result.append(getNCharacter(loStr,lo));
        }

        return result.toString();

    }

    private String getNCharacter(Character c, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < n; i++){
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        CodilityTest test = new CodilityTest();
        System.out.println(test.solution(5,3)); //aabaabab or abaabbaa
        System.out.println(test.solution(new int[] {-3, -14, -5, 7, 8, 42, 8, 3})); //SUMMER
    }
}
