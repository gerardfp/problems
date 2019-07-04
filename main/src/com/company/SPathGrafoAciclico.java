package com.company;

public class SPathGrafoAciclico {
//
    static int[][] G = {
            {0,3,0,1,0,0},
            {0,0,2,0,2,2},
            {0,0,0,0,3,1},
            {0,1,0,0,0,0},
            {0,0,0,0,0,5},
            {0,0,0,0,0,0}
    };

//    static int[][] G = {
//            {0,1,2,5},
//            {0,0,1,3},
//            {0,0,0,2},
//            {0,0,0,0}
//    };

    public static void main(String[] args) {
//        for (int i = 0; i < G.length; i++) {
//            for (int j = 0; j < G.length; j++) {
//                double d = shortestD(i, j);
//                double r = shortestR(i, j);
//                if(r != d){
//                    System.out.println();
//                    System.out.println("s,t = " + i + ","+j);
//                    System.out.println("D: " + d);
//                    System.out.println("R: " + r);
//                }
//
//            }
//        }
        System.out.println(shortestD(0,2));
    }

    static double shortestD(int s, int t){
        double[][] K = new double[G.length+1][G.length+1];

        for (int i = 0; i <= G.length; i++) {
            K[i][0] = Double.POSITIVE_INFINITY;
            K[0][i] = Double.POSITIVE_INFINITY;
        }

        K[0][s+1] = 0;

        for (int i = 1; i <= G.length; i++) {
            for (int j = 1; j <= G.length; j++) {
                if(G[i - 1][j - 1] > 0) {
                    K[i][j] = Math.min(K[i - 1][j], K[i - 1][i] + G[i - 1][j - 1]);
                } else {
                    K[i][j] = K[i-1][j];
                }
                Utils.printMatrix(K);
            }
        }
        return K[G.length][t+1];
    }

    static double shortestR(int s, int t){
        if(s==t) return 0;

        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < G.length; i++) {
            if(G[s][i] > 0){
                min = Math.min(min, G[s][i] + shortestR(i, t));
            }
        }
        return min;
    }
}
