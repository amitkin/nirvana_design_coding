package com.mylearning.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

class CheapestFlights {
    private class City implements Comparable<City>{
        int id;
        int costFromSrc;
        int stopFromSrc;

        public City(int id, int costFromSrc, int stopFromSrc){
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }
        public boolean equals(City c){
            return this.id == c.id;
        }

        public int compareTo(City c){
            return this.costFromSrc - c.costFromSrc;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] srcToDst = new int[n][n];
        for(int i = 0; i < flights.length; i++)
            srcToDst[flights[i][0]][flights[i][1]] = flights[i][2];

        PriorityQueue<City> minHeap = new PriorityQueue();
        minHeap.offer(new City(src,0,0));

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;

        while(!minHeap.isEmpty()){
            City curCity = minHeap.poll();
            if(curCity.id == dst) return curCity.costFromSrc;
            if(curCity.stopFromSrc == K + 1) continue;
            int[] nexts = srcToDst[curCity.id];
            for(int i = 0; i < n; i++){
                if(nexts[i] != 0){
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if(newCost < cost[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = newCost;
                    }
                    else if(newStop < stop[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }

        return cost[dst] == Integer.MAX_VALUE? -1:cost[dst];
    }

    public static void main(String[] args) {
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        CheapestFlights cheapestFlights = new CheapestFlights();
        System.out.println(cheapestFlights.findCheapestPrice(3, flights,0, 2, 1));
    }
}
