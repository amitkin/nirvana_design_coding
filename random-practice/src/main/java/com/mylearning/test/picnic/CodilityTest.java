package com.mylearning.test.picnic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CodilityTest {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int min = Integer.MAX_VALUE;
        for(int i=0; i< A.length; i++){
            if(A[i]%2 != 0 && A[i] < min){
                min = A[i];
            }
        }
        return min;
    }

    public int solution(int A){
        String s = String.valueOf(A);
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        int i = 0;
        int j = charArray.length- 1;
        while (i <= j ){
            if(i != j) {
                sb.append(charArray[i]);
                sb.append(charArray[j]);
            }
            else
                sb.append(charArray[i]);
            i++;
            j--;
        }
        String resultString = sb.toString();
        int result = Integer.valueOf(resultString);
        return result;
    }

    public static class Duration {
        int hour;
        int min;
        int sec;

        public Duration(int hour, int min, int sec) {
            this.hour = hour;
            this.min = min;
            this.sec = sec;
        }

        public int valueInSec(){
            return hour*3600 + min*60 + sec;
        }

        public int getMins(){
            return hour*60 + min;
        }

        public int getCost(){
            if(getMins() >= 5){
                int totalMins = min;
                if(sec > 0){
                    totalMins = min + 1;
                }
                return totalMins * 150;
            }
            else
            {
                return valueInSec()*3;
            }

        }
    }

    public int solution(String S){
        String[] logs = S.split("\n");
        SortedMap<String, Integer> numberToDurationMap = new TreeMap<>();
        Map<String,List<Duration>> numberToDurationList = new HashMap<>();
        for (String log: logs) {
            String[] split = log.split(",");
            String num = split[1];
            String duration = split[0];
            String[] time = duration.split(":");
            Duration d = new Duration(Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2]));
            List<Duration> durationList = numberToDurationList.getOrDefault(num, new ArrayList<>());
            durationList.add(d);
            numberToDurationList.put(num, durationList);
            Integer totalSecs = numberToDurationMap.getOrDefault(num, 0);
            totalSecs += d.valueInSec();
            numberToDurationMap.put(num, totalSecs);
        }

        int max = 0;
        String maxNum = null;
        for (Map.Entry<String, Integer> entry: numberToDurationMap.entrySet()) {
            int val = entry.getValue();
            if(val > max){
                maxNum = entry.getKey();
                max = val;
            }
        }

        numberToDurationList.remove(maxNum);

        int amount = 0;
        for (Map.Entry<String, List<Duration>> entry: numberToDurationList.entrySet()) {
            List<Duration> value = entry.getValue();
            for (Duration d : value) {
                amount += d.getCost();
            }
        }

        return amount;

    }

    public static void main(String[] args) {
        CodilityTest s = new CodilityTest();
        int arr[] = {-100, 2, 3, 4, 84, -10001, 0, -1, 473};
        System.out.println(s.solution(arr));
        System.out.println(s.solution(130));
        String str = "00:01:07,400-234-090\n"
                + "00:05:01,701-080-080\n"
                + "00:05:00,400-234-090";

        String str1 = "00:02:36,400-234-090\n"
                + "00:05:12,701-080-080\n"
                + "00:02:36,400-234-090";
        String str2 = "00:02:36,701-080-080\n"
                + "00:05:12,400-234-090\n"
                + "00:02:36,701-080-080";
        String str3 = "00:05:12,400-234-090\n"
                +"00:02:36,701-080-080\n"
                + "00:02:36,701-080-080";

        System.out.println(s.solution(str));
    }
}
