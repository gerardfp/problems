public class CaminoKAristas {

    static double minKEdgesDynamic(int[][] G, int T, int V, int K){
        double[][] D = new double[T+1][K+1];

        for (int v = 0; v <= T; v++) {
            for (int k = 0; k <= K; k++) {
                double min = Double.POSITIVE_INFINITY;
                for (int i = 0; i < G.length; i++) {
                    if(G[v][i] != 0 && k-1 > 0) {
                        min = Math.min(min, D[i][k] + G[v][i]);
                    }
                }
                D[v][k] = min;
            }
        }

        return D[T][K];
    }

    static double minKEdgesRecursiu(int[][] G, int T, int V, int K){
        if(V == T && K == 0) return 0;
        if(V != T && K == 0) return Double.POSITIVE_INFINITY;

        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < G.length; i++) {
            if(G[V][i] != 0) {
                min = Math.min(min, minKEdgesRecursiu(G, T, i, K - 1) + G[V][i]);
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int[][] graph = new int[6][6];
        graph[0][1] = 3;
        graph[0][3] = 1;

        graph[1][0] = 1;
        graph[1][1] = 2;
        graph[1][2] = 2;
        graph[1][4] = -2;
        graph[1][5] = 3;

        graph[2][5] = 1;

        graph[3][1] = 1;

        graph[4][3] = 2;
        graph[4][5] = 2;

        for (int k = 3; k < 4; k++) {
            System.out.println(minKEdgesRecursiu(graph, 5, 0, k));
        }
        System.out.println("---");

        for (int k = 3; k < 4; k++) {
            System.out.println(minKEdgesDynamic(graph, 5, 0, k));
        }
    }
}