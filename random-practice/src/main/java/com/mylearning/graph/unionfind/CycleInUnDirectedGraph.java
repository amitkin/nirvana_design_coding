package com.mylearning.graph.unionfind;

//https://leetcode.com/problems/graph-valid-tree/
class CycleInUnDirectedGraph {
    int V, E; // V-> no. of vertices & E->no.of edges
    Edge edge[]; // /collection of all edges

    class Edge
    {
        int src, dest;
    }

    // Creates a graph with V vertices and E edges
    CycleInUnDirectedGraph(int v,int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }

    // A utility function to find the subset of an element i recursively and does path compression as well
    int find(int parent[], int i)
    {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
            i = parent[i];
        }
        return i;
    }

    // A utility function to do union of two subsets
    void union(int parent[], int x, int y)
    {
        int xset = find(parent, x);
        int yset = find(parent, y);

        //if they are part of same set do nothing
        if(xset == yset) return;

        //else one of them's parent becomes parent of other
        parent[xset] = yset;
    }


    // The main function to check whether a given graph
    // contains cycle or not
    boolean hasCycle()
    {
        // Allocate memory for creating V subsets
        int parent[] = new int[V];

        // Initialize all subsets as single element sets with itself
        for (int i=0; i<V; ++i)
            parent[i]=i;

        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < E; ++i)
        {
            int x = find(parent, edge[i].src);
            int y = find(parent, edge[i].dest);

            if (x == y)
                return true;

            union(parent, x, y);
        }
        return false;
    }

    // Driver Method
    public static void main (String[] args)
    {
		/* Let us create following graph
		0
		| \
		|  \
		1---2 */
        int V = 3, E = 3;
        CycleInUnDirectedGraph graph = new CycleInUnDirectedGraph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;

        if (graph.hasCycle())
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }
}


