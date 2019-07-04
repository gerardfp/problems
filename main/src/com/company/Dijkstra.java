package com.company;

import java.util.Arrays;

public class Dijkstra {
    static double[][] G;

    public static void main(String[] args) {
        G = new double[6][6];
        G[0][1] = 10;
        G[0][2] = 2;
        G[2][1] = 10;
        G[1][3] = 2;
        G[4][1] = 10;
        G[2][3] = 2;
        G[2][4] = 10;
        G[3][5] = 10;
        G[4][5] = 2;

        G = new double[12][12];
        G[0][1] = 9;
        G[0][2] = 7;
        G[1][2] = 6;
        G[1][4] = 1;
        G[2][3] = 5;
        G[2][5] = 1;
        G[2][11] = 2;
        G[3][0] = 9;
        G[3][2] = 1;
        G[3][5] = 8;
        G[4][11] = 7;
        G[5][4] = 3;
        G[5][11] = 6;

        dijkstra(G);
    }

    static int minimum(boolean[] used, double[] dist){
        double min = Double.POSITIVE_INFINITY;
        int ind = -1;

        for (int i = 0; i < dist.length; i++) {
            if(!used[i] && dist[i] < min){
                min = dist[i];
                ind = i;
            }
        }

        return ind;
    }

    static void dijkstra(double[][] G){
        boolean[] used = new boolean[G.length];
        double[] dist = new double[G.length];
        int[] path = new int[G.length];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }

        dist[0] = 0;
        for (int i = 0; i < G.length; i++) {
            int min = minimum(used, dist);
            if(min == -1) break;

            used[min] = true;

            for (int j = 0; j < G.length; j++) {
                if(!used[j] && G[min][j] != 0 && dist[min]+G[min][j] < dist[j]){
                    dist[j] = dist[min]+G[min][j];
                    path[j] = min;
                }
            }
        }

        System.out.println(Arrays.toString(path));
        System.out.println(dist[G.length-1]);
    }
}
