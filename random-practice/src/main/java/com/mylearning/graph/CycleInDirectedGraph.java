package com.mylearning.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.youtube.com/watch?v=rKQaZuoUR4M
 */
//This has multiple solutions :
//1. https://www.geeksforgeeks.org/detect-cycle-in-a-directed-graph-using-bfs/ - same as topological sort
//2. https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
//3. https://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/
public class CycleInDirectedGraph {

    Graph graph;

    public CycleInDirectedGraph(Graph graph) {
        this.graph = graph;
    }

    public boolean hasCycle() {
        Set<Integer> whiteSet = new HashSet<>();
        Set<Integer> graySet = new HashSet<>();
        Set<Integer> blackSet = new HashSet<>();

        for (int i = 0; i < graph.getNoOfVertices() - 1 ; i++) {
            whiteSet.add(i+1);
        }

        while (whiteSet.size() > 0) {
            int current = whiteSet.iterator().next();
            if(dfs(current, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int current, Set<Integer> whiteSet, Set<Integer> graySet, Set<Integer> blackSet ) {
        //move current to gray set from white set and then explore it.
        moveVertex(current, whiteSet, graySet);
        for(Integer neighbor : graph.getNeighbours(current)) {
            //if in black set means already explored so continue.
            if (blackSet.contains(neighbor)) {
                continue;
            }
            //if in gray set then cycle found.
            if (graySet.contains(neighbor)) {
                return true;
            }
            if(dfs(neighbor, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        //move vertex from gray set to black set when done exploring.
        moveVertex(current, graySet, blackSet);
        return false;
    }

    private void moveVertex(int vertex, Set<Integer> sourceSet, Set<Integer> destinationSet) {
        sourceSet.remove(vertex);
        destinationSet.add(vertex);
    }

    public static void main(String args[]){
        Graph graph = new Graph(6);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        CycleInDirectedGraph cdg = new CycleInDirectedGraph(graph);

        if (cdg.hasCycle())
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }
}

