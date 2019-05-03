package com.mylearning.onlinetests.amazon;

import java.util.*;

public class MinCostSpanningTree {

    int getMinCost(int numAvailableCities, int numTotalAvailableRoads,
            List<List<Integer>> availableRoads, int newRoads,
            List<List<Integer>> newRoadsToConstruct) {

        //city => set of connected cities
        Map<Integer, Set<Integer>> vertexSets = new HashMap<Integer, Set<Integer>>();

        //initialize as city => city
        for (int i = 0; i < numAvailableCities; i++) {
            HashSet<Integer> value = new HashSet<Integer>();
            int city = i + 1;
            value.add(city);
            vertexSets.put(city, value);
        }

        //for each available road, update set of connected cities. If road creates a cycle, it's of no use and cities were already connected. 
        int noOfRoadsUsed = 0;
        for (List<Integer> availableRoad : availableRoads) {
            Set<Integer> set1 = vertexSets.get(availableRoad.get(0));
            Set<Integer> set2 = vertexSets.get(availableRoad.get(1));
            if (set1 != set2) { //just need to check the reference. 
                set1.addAll(set2);
                vertexSets.put(availableRoad.get(1), set1);
                noOfRoadsUsed++;
            }
        }

        //sort new roads by increasing order of cost.
        Collections.sort(newRoadsToConstruct, new Comparator<List<Integer>>() {
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2).compareTo(o2.get(2));
            }
        });


        int cost = 0;
        //for each new road, update set of connected cities. Ignore the ones creating cycle. 
        for (List<Integer> newRoad : newRoadsToConstruct) {
            if (noOfRoadsUsed < numAvailableCities - 1) {
                Set<Integer> set1 = vertexSets.get(newRoad.get(0));
                Set<Integer> set2 = vertexSets.get(newRoad.get(1));
                if (set1 != set2) {//just need to check the reference. 
                    set1.addAll(set2);
                    vertexSets.put(newRoad.get(1), set1);
                    noOfRoadsUsed++;
                    cost += newRoad.get(2);
                }
            } else {
                break;
            }
        }
        if (noOfRoadsUsed != numAvailableCities - 1) { //no mst found
            cost = -1;
        }
        return cost;
    }

    public static void main(String[] args) {
        MinCostSpanningTree solution = new MinCostSpanningTree();
        System.out.println(solution.getMinCost(6, 3,
                Arrays.asList(Arrays.asList(1, 4), Arrays.asList(4, 5), Arrays.asList(2, 3)),
                4,
                Arrays.asList(Arrays.asList(1, 2, 5), Arrays.asList(1, 3, 10), Arrays.asList(1, 6, 2), Arrays.asList(5, 6, 5))
        ));

    }

}