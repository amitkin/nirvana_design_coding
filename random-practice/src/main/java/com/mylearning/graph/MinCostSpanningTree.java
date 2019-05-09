package com.mylearning.graph;

import java.util.*;

/*
Calculate Minimum cost to add new roads between cities such that all the cities are accessible from each other.

It's kind of a modified minimum spanning tree. Here we have some predefined edges that are compulsory to use in minimum spanning tree.
Then we have some new Roads i.e some new edges, out of those edges we can choose the edges which connects all the cities in minimum cost.

In minimum spanning tree (MST) we need n-1 edges to connect n cities(vertexes).

Implementation is as follows :-
1) we created a map of city to connected cities. So initially each city is connected to itself only. Then we iterate through all the existing roads,
for each new road, update set of connected cities. Ignore the ones creating cycle. We are increasing the count of roads used also.
2) Sort new roads by increasing order of cost, so that we pick the new road with lowest cost first.
3) for each new road, update set of connected cities. Ignore the ones creating cycle and update the cost also.
4) In the end if number of roads used are not equal to (number of cities -1), then MST is not possible so we will return cost as -1, else just return the cost.
*/
public class MinCostSpanningTree {

    int getMinCost(int numTotalAvailableCities, int numTotalAvailableRoads, List<List<Integer>> roadsAvailable, int numNewRoadsConstruct, List<List<Integer>> costNewRoadsConstruct) {

        //city => set of connected cities
        Map<Integer, Set<Integer>> vertexSets = new HashMap<>();

        //initialize as city => city
        for (int i = 0; i < numTotalAvailableCities; i++) {
            HashSet<Integer> value = new HashSet<>();
            int city = i + 1;
            value.add(city);
            vertexSets.put(city, value);
        }

        //for each available road, update set of connected cities. If road creates a cycle, it's of no use and cities were already connected. 
        int noOfRoadsUsed = 0;
        for (List<Integer> availableRoad : roadsAvailable) {
            Set<Integer> set1 = vertexSets.get(availableRoad.get(0));
            Set<Integer> set2 = vertexSets.get(availableRoad.get(1));
            if (set1 != set2) { //just need to check the reference. 
                set1.addAll(set2);
                vertexSets.put(availableRoad.get(1), set1);
                vertexSets.put(availableRoad.get(0), set1);
                noOfRoadsUsed++;
            }
        }

        //sort new roads by increasing order of cost.
        Collections.sort(costNewRoadsConstruct, new Comparator<List<Integer>>() {
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2).compareTo(o2.get(2));
            }
        });


        int cost = 0;
        //for each new road, update set of connected cities. Ignore the ones creating cycle. 
        for (List<Integer> newRoad : costNewRoadsConstruct) {
            if (noOfRoadsUsed < numTotalAvailableCities - 1) {
                Set<Integer> set1 = vertexSets.get(newRoad.get(0));
                Set<Integer> set2 = vertexSets.get(newRoad.get(1));
                if (set1 != set2) {//just need to check the reference. 
                    set1.addAll(set2);
                    vertexSets.put(newRoad.get(1), set1);
                    vertexSets.put(newRoad.get(0), set1);
                    noOfRoadsUsed++;
                    cost += newRoad.get(2);
                }
            } else {
                break;
            }
        }
        if (noOfRoadsUsed != numTotalAvailableCities - 1) { //no mst found
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