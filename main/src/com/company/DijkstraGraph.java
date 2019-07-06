package com.company;

import java.util.LinkedList;
import java.util.List;

public class DijkstraGraph {

    static class Graph {
        int V;
        List<List<Integer>> adj = new LinkedList<>();

        Graph(int V){
            this.V = V;
            for (int i = 0; i < V; i++) {
                adj.add(new LinkedList<>());
            }
        }

        void addEdge(int u, int v){
            adj.get(u).add(v);
        }
    }

    public static void main(String[] args) {

    }
}
