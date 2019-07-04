package com.company;

import java.util.Arrays;

public class Radiodifusion {
    // https://runestone.academy/runestone/static/pythoned/Graphs/AlgoritmoDePrimDelArbolDeExpansion.html

    static int[][] G = {
            {0, 2, 3, 0, 0, 0, 0},
            {2, 0, 1, 1, 4, 0, 0},
            {3, 1, 0, 0, 0, 5, 0},
            {0, 1, 0, 0, 1, 0, 0},
            {0, 4, 0, 1, 0, 1, 0},
            {0, 0, 5, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 1, 0}
    };

    public static void main(String[] args) {
        primMst(G);
    }

    static int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < mstSet.length; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    static void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < G.length; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    static void primMst(int[][] G){
        boolean[] used = new boolean[G.length];
        int[] weights = new int[G.length];
        int[] path = new int[G.length];

        for (int i = 0; i < G.length; i++) {
            weights[i] = Integer.MAX_VALUE;
        }

        weights[0] = 0;

        for (int i = 0; i < G.length; i++) {
            int u = minKey(weights, used);

            used[u] = true;

            for (int j = 0; j < G.length; j++) {
                if(G[u][j] !=0 && !used[j] && G[u][j]<weights[j]){
                    path[j] = u;
                    weights[j] = G[u][j];
                }
            }
        }

        System.out.println(Arrays.toString(path));
        printMST(path, G);
    }
}
