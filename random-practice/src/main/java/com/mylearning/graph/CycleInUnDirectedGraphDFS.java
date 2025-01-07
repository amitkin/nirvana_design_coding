package com.mylearning.graph;

import java.util.HashSet;
import java.util.Set;

/*
If we encounter a visited vertex again, then we say, there is a cycle. But there is a catch in this algorithm,
we need to make sure that we do not consider every edge as a cycle because in an undirected graph, an edge
from 1 to 2 also means an edge from 2 to 1. To handle this, we keep track of the parent node (the node from which
we came to the current node) in the DFS traversal and ignore the parent node from the visited condition.

*/
public class CycleInUnDirectedGraphDFS {

    Graph graph;

    public CycleInUnDirectedGraphDFS(Graph graph) {
        this.graph = graph;
    }

    public boolean hasCycleDFS(){
        Set<Integer> visited = new HashSet<>();
        for(int vertex=0; vertex<graph.getNoOfVertices() -1; vertex++){
            if(!visited.contains(vertex)){
                if(hasCycleDFSUtil(vertex, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycleDFSUtil(int vertex, Set<Integer> visited, int parent){
        visited.add(vertex);
        for(int adj : graph.getNeighbours(vertex)){
            //the node from which we came to the current node, ignore the parent node from the visited condition
            if(adj == parent){
                continue;
            }
            //we encounter a visited vertex again so cycle is there
            if(visited.contains(adj)){
                return true;
            }
            boolean hasCycle = hasCycleDFSUtil(adj,visited,vertex);
            if(hasCycle){
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);

        CycleInUnDirectedGraphDFS c = new CycleInUnDirectedGraphDFS(graph);
        if (c.hasCycleDFS())
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }
}
