package com.company;

import java.util.LinkedList;
import java.util.List;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0
you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs,
is it possible for you to finish all courses?
 */
public class CourseSchedule {

    static class Graph {
        List<List<Integer>> adjList = new LinkedList<>();
        int V;

        Graph(int V){
            this.V = V;
            for (int i = 0; i < V; i++) {
                adjList.add(new LinkedList<>());
            }
        }

        void addEdge(int u, int v){
            adjList.get(u).add(v);
        }
    }

    static int n = 5;
    static int[][] pre = {
            {1,0},{2,3}
    };

    public static void main(String[] args) {
        Graph graph = new Graph(n);
        for (int i = 0; i < pre.length; i++) {
            graph.addEdge(pre[i][0], pre[i][1]);
        }

        System.out.println(hashCycle(graph));
    }

    static boolean hasCycleUtil(int v, boolean[] visited, boolean[] hasChildren, Graph graph){
        if(hasChildren[v]) return true;
        if(visited[v]) return false;

        visited[v] = true;
        hasChildren[v] = true;

        for (int i = 0; i < graph.adjList.get(v).size(); i++)
            if (hasCycleUtil(graph.adjList.get(v).get(i), visited, hasChildren, graph))
                return true;

        hasChildren[v] = false;
        return false;
    }

    static boolean hashCycle(Graph graph){
        boolean[] visited = new boolean[graph.V];
        boolean[] hasChildren = new boolean[graph.V];

        for (int i = 0; i < graph.V; i++)
            if(hasCycleUtil(i, visited, hasChildren, graph))
                return true;

        return false;
    }
}
