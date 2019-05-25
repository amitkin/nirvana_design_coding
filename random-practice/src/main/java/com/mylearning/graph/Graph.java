package com.mylearning.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
Time Complexity of DFS / BFS to search all vertices = O(E + V)
DFS will therefore use less memory O(log n) than BFS O(n).
*/
public class Graph {

    private int noOfVertices;

    // This keep the information of connected vertices,
    private Map<Integer, Set<Integer>> adjacencyListMap = new HashMap<>();

    // If instead of Integer, Character/String is there then use HashMap<Character, List/Set>

    public Graph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        for (int i=0; i<noOfVertices; ++i) {
            adjacencyListMap.put(i, new HashSet<>());
        }
    }

    public int getNoOfVertices() {
        return noOfVertices;
    }

    // Function to add an edge into the graph
    public void addEdge(int vertex, int destination)
    {
        //get the adjacency set of vertex and add the edge
        adjacencyListMap.getOrDefault(vertex, new HashSet<>()).add(destination);
    }

    public Set<Integer> getNeighbours(int vertex) {
        return adjacencyListMap.getOrDefault(vertex, Collections.emptySet());
    }

    public List<Integer> bfs(int vertex) {
        List<Integer> result = new ArrayList<>();

        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[noOfVertices];

        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Mark the current vertex as visited and enqueue it
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
            Iterator<Integer> i = adjacencyListMap.get(vertex).iterator();
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

    void dfsUtil(int vertex, boolean visited[], List<Integer> vertices){

        // Mark the current vertex as visited and print it
        visited[vertex] = true;
        vertices.add(vertex);

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adjacencyListMap.get(vertex).iterator();
        while (i.hasNext())
        {
            int nextVertex = i.next();
            if (!visited[nextVertex]) {
                dfsUtil(nextVertex, visited, vertices);
            }
        }
    }

    // Time Complexity: The above algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E).

    // Applications: Topological Sorting is mainly used for scheduling jobs from the given dependencies among jobs.
    // Applications: In most academic programs there are prerequisite courses for taking a specific course. The prerequisite course(s)
    // needs to be completed before taking the course. Topological sorting can give you the sequence in which different courses can
    // be taken by students so that they can complete the pre-requisite courses before taking a course.

    // For example : determining the order of compilation tasks to perform in makefiles
    // Topological Sorting for a graph is not possible if the graph is not a Directed Acyclic Graph.
    void topologicalSortDFS()
    {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[noOfVertices];

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices one by one
        for (int vertex = 0; vertex < noOfVertices; vertex++)
            if (!visited[vertex]) {
                topologicalSortUtil(vertex, visited, stack);
            }

        // Print contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }

    // A recursive function used by topologicalSortDFS
    private void topologicalSortUtil(int vertex, boolean visited[], Stack<Integer> stack)
    {
        // Mark the current vertex as visited.
        visited[vertex] = true;

        // Recur for all the vertices adjacent to this vertex
        for (Integer neighbor : adjacencyListMap.get(vertex)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }

        // Push current vertex to stack which stores result
        stack.push(vertex);
    }

    // BFS approach for topological Sort
    // Start with vertices of which the indegree is 0, meaning no other vertices direct to them. Be sure to add these vertices to your result first.
    // You can use a HashMap to map every vertex with its indegree, and a queue which is very commonly seen in BFS to assist your traversal.
    // When you poll a vertex from the queue, the indegree of its neighbors need to be decreased by 1, this is like delete the vertex from the graph
    // and delete the edge between the vertex and its neighbors. Every time you come across vertices with 0 indegree, offer them to the queue for
    // checking their neighbors later and add them to the result.
    public ArrayList<Integer> topologicalSortBFS() {

        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        //mapping vertex to its indegree to the HashMap, however these vertices
        //have to be directed to by one other vertex, vertices whose indegree == 0
        //would not be mapped.
        for (int vertex = 0; vertex < noOfVertices; vertex++) {
            for (Integer neighbor : adjacencyListMap.get(vertex)) {
                if (indegree.containsKey(neighbor)) {
                    indegree.put(neighbor, indegree.get(neighbor) + 1);
                } else {
                    indegree.put(neighbor, 1);
                }
            }
        }

        //find all vertices with indegree == 0. They should be at starting positon in the result
        for (int vertex = 0; vertex < noOfVertices; vertex++) {
            if (!indegree.containsKey(vertex)){
                queue.offer(vertex);
            }
        }

        //everytime we poll out a vertex from the queue, it means we delete it from the
        //graph, we will minus its neighbors indegree by one, this is the same meaning
        //as we delete the edge from the vertex to its neighbors.
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            Iterator<Integer> neighbors = adjacencyListMap.get(vertex).iterator();
            while (neighbors.hasNext()) {
                int neighbor = neighbors.next();
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0){
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);

        /*g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);*/

        //With cycle
        /*g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);*/

        //DAG
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 4);

        System.out.println(g.dfs(2));
        System.out.println(g.bfs(2));
        g.topologicalSortDFS();
        System.out.println();
        g.topologicalSortBFS().forEach(e -> System.out.print(e + " "));
    }
}


