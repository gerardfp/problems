package com.company;

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

        return K[weight];
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

        return K[val.length][W];
    }

    static int bounded(int[] val, int[] wt, int[] qt, int W) {
        int[][] K = new int[val.length+1][W+1];

        for (int i = 1; i <= val.length; i++) {
            for (int w = 1; w <= W; w++) {
                int maxvalue = K[i-1][w];
                for (int n = 0; n <= qt[i-1] && wt[i-1]*n <= w; n++) {
                    maxvalue = Math.max(maxvalue, val[i-1] * n + K[i-1][w - wt[i-1] * n]);
                }
                K[i][w] = maxvalue;
            }
        }

        return K[val.length][W];
    }

    public static void main(String[] args) {
	    int[] quantity = {3, 1, 1, 2};
	    int[] values = {2, 5, 6, 7};
	    int[] weights = {1, 3, 4, 5};

        System.out.println(unbounded(values, weights, 4));
        System.out.println(zeroone(values, weights, 4));
        System.out.println(bounded(values, weights, quantity, 6));
    }
}