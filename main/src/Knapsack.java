public class Knapsack {

    static int unbounded(int[] values, int[] weights, int weight){
        int[] K = new int[weight+1];

        for (int i = 1; i <= weight; i++) {
            int maxvalue = Integer.MIN_VALUE; // TODO: ¿ 0 ?

            for (int j = 0; j < values.length && j<i; j++) {  // TODO: ¿ j<i ?
                if(i-weights[j] >= 0)
                    maxvalue = Math.max(maxvalue, values[j] + K[i-weights[j]]);
            }

            K[i] = maxvalue;
        }

        return K[K.length-1];
    }

    static int zeroone(int[] val, int[] wt, int W) {
        int[][] K = new int[val.length+1][W+1];

        for (int i = 1; i <= val.length; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i-1] <= w)
                    K[i][w] = Math.max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
        }

        return K[K.length-1][W];
    }

    public static void main(String[] args) {
	    int[] number = {4,7,3};
	    int[] values = {3, 6, 7};
	    int[] weights = {1,3,4};

        System.out.println(unbounded(values, weights, 4));
        System.out.println(zeroone(values, weights, 4));
    }

    static void printMatrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%3d ", m[i][j]);
            }
            System.out.println();
        }
        System.out.println("----");
    }
}