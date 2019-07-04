package com.company;

public class MatameCamion {
    static double[][] G;

    public static void main(String[] args) {
//        G = new double[6][6];
//        G[0][1] = 30;
//        G[0][2] = 40;
//        G[1][3] = 30;
//        G[1][4] = 40;
//        G[2][1] = 40;
//        G[2][3] = 40;
//        G[2][4] = 30;
//        G[3][5] = 30;
//        G[4][3] = 40;
//        G[4][5] = 40;

        G = new double[10][10];
        G[0][1] = 10;
        G[0][2] = 40;
        G[1][3] = 10;
        G[2][5] = 40;
        G[3][2] = 10;
        G[3][4] = 40;
        G[3][5] = 10;
        G[4][1] = 10;
        G[4][6] = 10;
        G[4][7] = 40;
        G[5][8] = 40;
        G[6][3] = 40;
        G[6][7] = 10;
        G[6][9] = 10;
        G[7][9] = 40;
        G[8][6] = 40;
        G[8][9] = 10;

        dijkstra(G);
    }

    static int maximum(boolean[] used, double[] carga){
        double max = Double.NEGATIVE_INFINITY;
        int ind = -1;

        for (int i = 0; i < carga.length; i++) {
            if(!used[i] && carga[i] > max){
                max = carga[i];
                ind = i;
            }
        }

        return ind;
    }

    static void dijkstra(double[][] G){
        boolean[] used = new boolean[G.length];
        double[] carga = new double[G.length];
        int[] path = new int[G.length];

        for (int i = 0; i < carga.length; i++) {
            carga[i] = Double.NEGATIVE_INFINITY;
        }

        carga[0] = Double.POSITIVE_INFINITY;
        for (int i = 0; i < G.length; i++) {
            int max = maximum(used, carga);
            if(max == -1) break;

            used[max] = true;

            for (int j = 0; j < G.length; j++) {
                if(!used[j] && G[max][j] != 0 && carga[max] > carga[j]){
                    carga[j] = Math.min(carga[max], G[max][j]);
                    path[j] = max;
                }
            }
        }

        printPath(path);
        System.out.println(carga[G.length-1]);
    }

    static void printPath(int[] path){
        int e = path[path.length-1];
        while(e != 0){
            System.out.println(e);
            e=path[e];
        }
    }
}
