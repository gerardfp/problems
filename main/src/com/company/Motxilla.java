package com.company;


public class Motxilla {
    public static void main(String[] args) {
        int[] w = {7,11,13};
        int W = 30;

        System.out.println(max(W,w));
    }

    static int max(int k, int[] w) {
        int val, max = 0;
        for (int value : w) if (k - value >= 0 && (val = max(k - value, w) + value) > max) max = val;
        return max;
    }

    public static int maxD(int k, int[] arr){
        int[] K = new int[k+1];

        for (int i = 1; i < k+1; i++)
            for (int j = 1; j < arr.length+1; j++)
                if(arr[j-1] <= i)
                    K[i] = Math.max(K[i], K[i-arr[j-1]] + arr[j-1]);

        return K[k];
    }
}

//    public static int maxMatriu(int k, int[] arr){
//        double[][] K = new double[arr.length+1][k+1];
//
//        for (int i = 1; i < arr.length+1; i++) {
//            for (int j = 1; j < k+1; j++) {
//                double maxD = Double.NEGATIVE_INFINITY;
//                for (int p = 0; p <= j/arr[i-1]; p++) {
//                    maxD = Math.maxD(maxD, K[i-1][j-p*arr[i-1]]+p*arr[i-1]);
//                }
//                K[i][j] = maxD;
//            }
//        }
//        return (int) K[arr.length][k];
//    }