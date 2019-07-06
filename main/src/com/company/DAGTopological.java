package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DAGTopological {

    class AdjNode {
        int v;
        int w;
        AdjNode(int _v, int _w) { v = _v;  w = _w; }
    }


    class Graph {
        private int V;
        private List<List<AdjNode>> adj;

        Graph(int v) {
            V=v;
            adj = new LinkedList<>();
            for (int i=0; i<v; ++i) adj.add(new LinkedList<>());
        }
        void addEdge(int u, int v, int weight) {
            AdjNode node = new AdjNode(v,weight);
            adj.get(u).add(node);
        }

        void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
            visited[v] = true;

            for (AdjNode node : adj.get(v))
                if (!visited[node.v])
                    topologicalSortUtil(node.v, visited, stack);

            stack.push(v);
        }


        void shortestPath(int s) {
            Stack<Integer> stack = new Stack<>();
            int dist[] = new int[V];

            boolean visited[] = new boolean[V];

            for (int i = 0; i < V; i++)
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);

            System.out.println(Arrays.toString(stack.toArray()));

            for (int i = 0; i < V; i++)
                dist[i] = Integer.MAX_VALUE;
            dist[s] = 0;

            int u;
            while (!stack.empty())
                if (dist[(u = stack.pop())] != Integer.MAX_VALUE)
                    for (AdjNode i : adj.get(u))
                        if (dist[i.v] > dist[u] + i.w)
                            dist[i.v] = dist[u] + i.w;


            for (int i = 0; i < V; i++)
                if (dist[i] == Integer.MAX_VALUE)
                    System.out.print( "INF ");
                else
                    System.out.print( dist[i] + " ");
        }
    }

    Graph newGraph(int number) {
        return new Graph(number);
    }

    public static void main(String args[]) {

        DAGTopological t = new DAGTopological();
//        Graph g = tarta.newGraph(6);
//        g.addEdge(0, 1, 5);
//        g.addEdge(0, 2, 3);
//        g.addEdge(1, 3, 6);
//        g.addEdge(1, 2, 2);
//        g.addEdge(2, 4, 4);
//        g.addEdge(2, 5, 2);
//        g.addEdge(2, 3, 7);
//        g.addEdge(3, 4, -1);
//        g.addEdge(4, 5, -2);
        Graph g = t.newGraph(4);
        g.addEdge(1,0,2);
        g.addEdge(2,1,2);
        g.addEdge(2,3,2);
        g.addEdge(3,0,2);
        int s = 1;
        System.out.println("Following are shortest distances "+ "from source " + s );
        g.shortestPath(s);
    }
}
