package com.company;

import javafx.util.Pair;

import java.util.*;

public class DijkstraGraph {

    static class Graph {
        class AdjNode {
            int v, w;
            AdjNode(int v, int w){ this.v=v; this.w=w;}
        }
        int v;
        List<List<AdjNode>> adj = new LinkedList<>();

        Graph(int V){
            this.v = V;
            for (int i = 0; i < V; i++) adj.add(new LinkedList<>());
        }

        void addEdge(int u, int v, int w){
            adj.get(u).add(new AdjNode(v,w));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0,1,10);
        graph.addEdge(0,2,10);
        graph.addEdge(1,3,20);
        graph.addEdge(2,3,10);
        graph.addEdge(3,4,30);
        graph.addEdge(3,5,30);
        graph.addEdge(4,6,40);
        graph.addEdge(5,6,30);
        graph.addEdge(5,2,30);
        graph.addEdge(0,5,10);

        spt(graph);
    }

    private static void spt(Graph graph) {
        boolean[] visited = new boolean[graph.v];
        int[] distance = new int[graph.v];
        int[] path = new int[graph.v];

        for (int i = 1; i < graph.v; i++) distance[i] = Integer.MAX_VALUE;

        PriorityQueue<Pair<Integer,Integer>> pq = new PriorityQueue<>(graph.v, Comparator.comparingInt(Pair::getKey));

        pq.offer(new Pair<>(distance[0], 0));

        int n=graph.v;
        while (!pq.isEmpty() & n>0){
            Pair<Integer, Integer> min = pq.poll();

            int u = min.getValue();
            if(!visited[u]) {
                visited[u] = true;
                n--;

                for (Graph.AdjNode adjNode : graph.adj.get(u)) {
                    if (!visited[adjNode.v] && distance[u] + adjNode.w < distance[adjNode.v]) {
                        distance[adjNode.v] = distance[u] + adjNode.w;
                        pq.offer(new Pair<>(distance[u] + adjNode.w, adjNode.v));
                        path[adjNode.v] = u;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(path));
    }
}
