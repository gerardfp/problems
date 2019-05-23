package com.company;

public class CaminoMasCortoSinCiclosNegativos {

    static int[][] G;

    static double bellmanFord(int S, int T) {
        /*https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/*/
        double dist[] = new double[G.length];

        for (int i=0; i < G.length; ++i)
            dist[i] = Double.POSITIVE_INFINITY;
        dist[S] = 0;

        for (int k = 1; k < G.length; k++) {
            for (int i = 0; i < G.length; i++) {
                for (int j = 0; j < G.length; j++) {
                    System.out.print("PASADA " + k +","+i+","+j+ ":   ");
                    if (G[i][j] != 0 && dist[i] != Double.POSITIVE_INFINITY && dist[i] + G[i][j] < dist[j]) {
                        dist[j] = dist[i] + G[i][j];
                    }
                    Util.printArray(dist);
                }
            }
        }

        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G.length; j++) {
                if (G[i][j] != 0 && dist[i] != Double.POSITIVE_INFINITY && dist[i] + G[i][j] < dist[j])
                    System.out.println("G contains negative weight cycle");
            }
        }

        return dist[T];
    }

    static double bellmanFordPath(int S, int T) {
        double dist[] = new double[G.length];
        int path2[] = new int[G.length];

        for (int i=0; i<G.length; ++i)
            dist[i] = Double.POSITIVE_INFINITY;
        dist[S] = 0;

        for (int k = 1; k < G.length; k++) {
            for (int i=0; i<G.length; ++i) {
                for (int j=0; j<G.length; ++j) {
                    if (G[i][j] != 0 && dist[i] != Double.POSITIVE_INFINITY && dist[i] + G[i][j] < dist[j]) {
                        dist[j] = dist[i] + G[i][j];
                        path2[j] = i;
                    }
                }
            }
        }

        for (int i = 0; i < G.length; i++) {
            for (int j=0; j<G.length; ++j) {
                if (G[i][j] != 0 && dist[i] != Double.POSITIVE_INFINITY && dist[i] + G[i][j] < dist[j])
                    System.out.println("G contains negative weight cycle");
            }
        }

        Util.printArray(path2);
        return dist[T];
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

        System.out.println("Bellmanford      " + bellmanFord(0, 5));
//        for (int i = 0; i < G.length; i++) {
//            for (int j = 0; j < G.length; j++) {
//                System.out.println("*** " + i + " -> " + j + " ***");
//                System.out.println("Bellmanford      " + bellmanFord(i, j));
//                System.out.println("BellmanfordPath  " + bellmanFordPath(i, j));
//                System.out.println();
//            }
//        }
    }
}