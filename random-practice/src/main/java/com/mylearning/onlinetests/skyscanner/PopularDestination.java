package com.mylearning.onlinetests.skyscanner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
Input:
6
Barcelona
Edinburg
Barcelona
Miami
Miami
Barcelona

Output:
Barcelona
*/
public class PopularDestination {

    static void OutputMostPopularDestination(int count, Scanner in) {
        ArrayList<String> destinations = new ArrayList<>();
        while(in.hasNextLine()){
            destinations.add(in.nextLine());
        }
        String mostPopularDestination
                = destinations.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Entry::getValue))
                .get()
                .getKey();
        System.out.println(mostPopularDestination);
    }

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int _count;
        _count = Integer.parseInt(in.nextLine());

        OutputMostPopularDestination(_count, in);
    }
}
