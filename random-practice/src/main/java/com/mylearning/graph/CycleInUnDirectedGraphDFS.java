package com.mylearning.graph;

import java.util.HashSet;
import java.util.Set;

public class CycleInUnDirectedGraphDFS {

    Graph graph;

    public CycleInUnDirectedGraphDFS(Graph graph) {
        this.graph = graph;
    }

    public boolean hasCycleDFS(){
        Set<Integer> visited = new HashSet<>();
        for(int vertex=0; vertex<graph.getNoOfVertices() -1; vertex++){
            if(visited.contains(vertex)){
                continue;
            }
            boolean flag = hasCycleDFSUtil(vertex, visited, -1);
            if(flag){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycleDFSUtil(int vertex, Set<Integer> visited, int parent){
        visited.add(vertex);
        for(int adj : graph.getNeighbours(vertex)){
            if(adj == parent){
                continue;
            }
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
