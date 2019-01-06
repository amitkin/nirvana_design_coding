package com.mylearning.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    private int noOfVertices;

    // This keep the information of connected nodes,
    // for example 0->1 means 1 is connected to 0 and in adjacency list of 0
    private List<LinkedList<Integer>> adjacencyList;

    // If instead of integer, character/string is there then use map<character, list/set>
    // instead of list which will work in O(1)

    public Graph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        adjacencyList = new ArrayList<>(noOfVertices);
        for (int i=0; i<noOfVertices; ++i) {
            adjacencyList.add(new LinkedList<>());
        }
    }

    // Function to add an edge into the graph
    private void addEdge(int vertex, int edgeWeight)
    {
        //get the adjacency list of vertex and add the edge
        adjacencyList.get(vertex).add(edgeWeight);
    }

    public List<Integer> bfs(int vertex) {
        List<Integer> result = new ArrayList<>();

        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[noOfVertices];

        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[vertex]=true;
        queue.add(vertex);

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            vertex = queue.poll();
            result.add(vertex);

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adjacencyList.get(vertex).listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return result;
    }


    public List<Integer> dfs(int vertex) {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[this.noOfVertices];

        // Call the recursive helper function to print DFS traversal
        List<Integer> result = new ArrayList<>();
        dfsUtil(vertex, visited, result);
        return result;
    }

    void dfsUtil(int vertex, boolean visited[], List<Integer> nodes){

        // Mark the current node as visited and print it
        visited[vertex] = true;
        nodes.add(vertex);

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adjacencyList.get(vertex).listIterator();
        while (i.hasNext())
        {
            int nextVertex = i.next();
            if (!visited[nextVertex]) {
                dfsUtil(nextVertex, visited, nodes);
            }
        }
    }

    // Time Complexity: The above algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E).
    // Applications: Topological Sorting is mainly used for scheduling jobs from the given dependencies among jobs.
    // For example : determining the order of compilation tasks to perform in makefiles
    void topologicalSort()
    {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[noOfVertices];

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices one by one
        for (int i = 0; i < noOfVertices; i++)
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }

        // Print contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }

    // A recursive function used by topologicalSort
    private void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack)
    {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adjacencyList.get(v).iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Push current vertex to stack which stores result
        stack.push(v);
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println(g.dfs(2));
        System.out.println(g.bfs(2));
        g.topologicalSort();
    }
}


