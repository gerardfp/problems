package com.company;


public class KnapsackHackerrank {

   static int[] arr = {3,4,4,4,8};
   static int k = 13;


    public static void main(String[] args) {
        System.out.println(max(k, arr));
    }

    public static int max(int k, int[] arr){
        double[] K = new double[k+1];

        for (int i = 1; i < k+1; i++) {
            for (int j = 1; j < arr.length+1; j++) {
                if(arr[j-1] <= i)
                    K[i] = Math.max(K[i], K[i-arr[j-1]] + arr[j-1]);
            }
        }
        return (int) K[k];
    }

    public static int maxMatriu(int k, int[] arr){
        double[][] K = new double[arr.length+1][k+1];

        for (int i = 1; i < arr.length+1; i++) {
            for (int j = 1; j < k+1; j++) {
                double max = Double.NEGATIVE_INFINITY;
                for (int p = 0; p <= j/arr[i-1]; p++) {
                    max = Math.max(max, K[i-1][j-p*arr[i-1]]+p*arr[i-1]);
                }
                K[i][j] = max;
            }
        }
        return (int) K[arr.length][k];
    }
}
