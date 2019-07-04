package com.company;


public class Knapsack2 {

    static int capacity = 6;
    static int[] values = {10,20,30};
    static int[] weights = {2,3,5};
    static int[] nitems = {2,1,1};
    static int[] picklist = {0,0,0};

    public static void main(String[] args) {
//        System.out.println(unboundedRecursive(0));
//        System.out.println(unboundedRecursivePickList(0, 0, 1));
//        System.out.println(unboundedDynamic());
//        System.out.println(unboundedWithPathDynamic());
        System.out.println(zerooneRecursive(0, 2));
        System.out.println("ZD:" + zerooneDynamic());
    }

    static double unboundedDynamic(){
        double[][] K = new double[weights.length+1][capacity+1];

        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                double max = Double.NEGATIVE_INFINITY;
                for (int k = 0; k <= j; k++) {
                    max = Math.max(max, K[i-1][k]+((j-k)/weights[i-1]*values[i-1]));
                }
                K[i][j] = max;
            }
        }

        return K[weights.length][capacity];
    }



    static double unboundedWithPathDynamic(){
        double[] K = new double[capacity+1];
        int[] I = new int[capacity+1];
        int[] B = new int[capacity+1];

        double val;
        for (int i = 1; i <= capacity; i++)
            for (int j = 0; j < weights.length; j++)
                if (weights[j] <= i && (val = K[i-weights[j]] + values[j]) > K[i]){
                    K[i] = val;
                    I[i] = j;
                    B[i] = i-weights[j];
                }

        int[] pickList = new int[weights.length];
        int b = B.length-1;
        pickList[I[b]]++;
        while(B[b] != 0) pickList[I[b = B[b]]]++;

        return K[capacity];
    }





    static double unboundedRecursive(int weight){
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < weights.length; i++)
            if (weight + weights[i] <= capacity)
                max= Math.max(max, unboundedRecursive(weight+weights[i]) + values[i]);

        if(max == Double.NEGATIVE_INFINITY) return 0;

        return max;
    }

    static double unboundedRecursivePickList(int weight, int item, int l){
        System.out.format("%"+(l*4)+"s call %d %d%n", "", weight, weights[item]);

        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < weights.length; i++) {
            if (weight + weights[i] <= capacity) {
                double value = unboundedRecursivePickList(weight + weights[i], i, l+1) + values[i];
                System.out.format("%"+(l*4)+"s value %.0f   %d%n", "", value, i);
                if (value > max) {
                    max = value;
                    picklist[i]++;
                }
            }
        }

        if(max == Double.NEGATIVE_INFINITY) return 0;

        return max;
    }


    static double zerooneRecursive(int weight, int n){
        if(weight > capacity || n < 0) return 0;
        if(weight + weights[n] > capacity) return zerooneRecursive(weight, n-1);

        return Math.max(zerooneRecursive(weight+weights[n], n-1)+values[n], zerooneRecursive(weight, n-1));
    }

    static double zerooneDynamic(){
        double[][] K = new double[weights.length+1][capacity+1];

        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= capacity ; j++) {
                if(weights[i-1] <= j){
                    K[i][j] = Math.max(values[i-1]+K[i-1][j-weights[i-1]],K[i-1][j]);
                }
            }
        }

//        Utils.printMatrix(K);

        return K[weights.length][capacity];
    }
}
