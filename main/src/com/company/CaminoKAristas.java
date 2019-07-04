package com.company;

public class CaminoKAristas {
    
    static int[][] G;

    static double minKEdgesDynamic(int S, int T, int K){
        double[][] DP = new double[K+1][G.length];

        for (int i = 0; i < G.length; i++) {
            if(i != S)
                DP[0][i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 1; i < K+1; i++) {
            for (int j = 0; j < G.length; j++) {
                double min = Double.POSITIVE_INFINITY;
                for (int k = 0; k < G.length; k++) {
                    if(G[k][j] != 0) {
                        min = Math.min(min, DP[i-1][k]+G[k][j]);
                    }
                }
                DP[i][j] = min;
            }
        }

        return DP[K][T];
    }

    static double minKEdgesRecursiu(int S, int T, int K){
        if(S == T && K == 0) return 0;
        if(S != T && K == 0) return Double.POSITIVE_INFINITY;

        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < G.length; i++) {
            if(G[S][i] != 0) {
                min = Math.min(min, minKEdgesRecursiu(i, T, K - 1) + G[S][i]);
            }
        }
        return min;
    }

    static double minKEdgesRecursiuLog(int S, int T, int K, int l){
        System.out.printf("%"+l+"s%s\n", " ", "CALL " + S + "  " + K);
        if(S == T && K == 0) return 0;
        if(S != T && K == 0) return Double.POSITIVE_INFINITY;

        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < G.length; i++) {
            if(G[S][i] != 0) {
                min = Math.min(min, minKEdgesRecursiuLog(i, T, K - 1, l+1) + G[S][i]);
            }
        }
        return min;
    }


    public static void main(String[] args) {
        G = new int[6][6];
        G[0][1] = 3;
        G[0][3] = 1;

        G[1][0] = 1;
        G[1][1] = 2;
        G[1][2] = 2;
        G[1][4] = -2;
        G[1][5] = 3;

        G[2][5] = 1;

        G[3][1] = 1;

        G[4][3] = 2;
        G[4][5] = 2;

        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G.length; j++) {
                for (int k = 0; k < 6; k++) {
                    System.out.println("*** i=" + i +" j=" + j + " k=" + k + " ***");
                    System.out.println("RECURSIU  " + minKEdgesRecursiu(i, j, k));
                    System.out.println("DYNAMIC   " + minKEdgesDynamic(i, j, k));
                    System.out.println();
                }
            }
        }
    }
}