import java.util.Arrays;

public class Knapsack {

    static int unbounded(int[] val, int[] wt, int W) {
        int[] K = new int[W + 1];

        for (int i = 0; i <= W; i++) {
            int max_val_for_item = 0;
            for (int j = 0; j < val.length; j++) {
                if(i >= wt[j]){
                    int item_value = val[j];
                    int max_with_space_left = K[i - wt[j]];
                    max_val_for_item = Math.max(max_val_for_item, item_value + max_with_space_left);
                }
            }
            K[i] = max_val_for_item;
        }
        System.out.println(Arrays.toString(K));
        return K[W];
    }

    static int knapSack01(int val[], int wt[], int W) {
        int K[][] = new int[val.length+1][W+1];

        for (int i = 1; i < K.length; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i-1] <= w)
                    K[i][w] = Math.max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
                printMatrix(K);
                System.out.println("--");
            }
        }

        printMatrix(K);
        return K[K.length-1][W];
    }

    public static void main(String[] args) {
	    int[] number = {4,7,3};
	    int[] values = {2, 6, 7};
	    int[] weights = {1,3,4};

        System.out.println(unbounded(values, weights, 4));
        System.out.println(knapSack01(values, weights, 4));
    }

    static void printMatrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%3d ", m[i][j]);
            }
            System.out.println();
        }
    }
}