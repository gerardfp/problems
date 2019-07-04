package com.company;

public class FutbolDijkstra {

    static double[][] G;
    public static void main(String[] args) {
//        G = new double[12][12];
//        G[0][1] = 0.9;
//        G[0][2] = 0.7;
//        G[1][2] = 0.6;
//        G[1][4] = 0.1;
//        G[2][3] = 0.5;
//        G[2][5] = 0.1;
//        G[2][11] = 0.2;
//        G[3][0] = 0.9;
//        G[3][2] = 1;
//        G[3][5] = 0.8;
//        G[4][11] = 0.7;
//        G[5][4] = 0.3;
//        G[5][11] = 0.6;

        G = new double[6][6];
        G[0][1] = 0.1;
        G[0][2] = 0.9;
        G[2][1] = 0.1;
        G[1][3] = 0.9;
        G[4][1] = 0.9;
        G[2][3] = 0.1;
        G[2][4] = 0.9;
        G[3][5] = 0.9;
        G[4][5] = 0.1;

        System.out.println(optim(0, 11));
        System.out.println(dijkstra());
    }

    static double optim(int s, int k){
        if(s==G.length-1) return 1;
        if(k==0) return 0;

        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < G.length; i++)
            if(G[s][i] > 0)
                max = Math.max(max, G[s][i] * optim(i, k-1));
        return max;
    }

    static int maxProb(double[] dist, boolean[] included) {
        double max = Double.NEGATIVE_INFINITY;
        int maxIndex = -1;

        for (int v = 0; v < G.length; v++)
            if (!included[v] && dist[v] >= max) {
                max = dist[v];
                maxIndex = v;
            }

        return maxIndex;
    }

    static double dijkstra(){
        double[] dist = new double[G.length];
        boolean[] included = new boolean[G.length];

        dist[0] = 1;

        for (int i = 0; i < G.length-1; i++) {
            int min = maxProb(dist, included);

            if(min == -1) break;

            included[min] = true;

            for (int j = 0; j < G.length; j++)
                if(!included[j] && G[min][j] != 0 && dist[min] != 0 && dist[min]*G[min][j] > dist[j])
                    dist[j] = dist[min]*G[min][j];

        }

        return dist[G.length-1];
    }
}
