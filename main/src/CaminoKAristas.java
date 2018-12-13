public class CaminoKAristas {
    
    static int[][] G;

    static double minKEdgesDynamic(int S, int T, int K){
        double[][] D = new double[T+1][K+1];

        for (int k = 0; k <= K; k++) {
            for (int s = 0; s <= T; s++) {
                if (s==T && k==0) D[s][k] = 0;
                else if (s!=T && k==0) D[s][k] = Double.POSITIVE_INFINITY;
                else {
                    double min = Double.POSITIVE_INFINITY;
                    for (int i = 0; i < G.length; i++) {
                        if (G[s][i] != 0 && k - 1 >= 0) {
                            min = Math.min(min, D[i][k-1] + G[s][i]);
                        }
                    }
                    D[s][k] = min;
                }
            }
        }

        return D[S][K];
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

        for (int k = 0; k < 6; k++) {
            System.out.println("*** k=" + k + " ***");
            System.out.println("RECURSIU  " + minKEdgesRecursiu(1, 5, k));
            System.out.println("DYNAMIC   " + minKEdgesDynamic(1, 5, k));
            System.out.println();
        }
    }
}