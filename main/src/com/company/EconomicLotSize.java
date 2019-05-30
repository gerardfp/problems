package com.company;

import java.util.Arrays;

public class EconomicLotSize {
    static int[] d = {3,2,3};   // demanda
    static int[] max;
    static int[] ca = {1,4,1};    // cost magatzem
    static int[] cp = {5,1,2};  // cost produccio

    public static void main(String[] args) {

//        max = new int[d.length];
//        max[d.length-1] = d[d.length-1];
//        for (int i = d.length-2; i >= 0; i--) {
//            max[i] = max[i+1] + d[i];
//        }
//
//        System.out.println(Arrays.toString(max));
        System.out.println(optimize());
    }

    static double optimize(){
        double[] prod = new double[d.length];

        prod[0] = d[0]*cp[0];

        for (int i = 1; i < d.length; i++) {
            double min = Double.POSITIVE_INFINITY;

            for (int j = 0; j <= i; j++) {
                int c = d[i]*cp[j];
                for (int k = j; k <i ; k++) {
                    c += d[i]*ca[k];
                }
                min = Math.min(min, c);
            }
            prod[i] = min;
        }

        System.out.println(Arrays.toString(prod));
        return d[d.length-1];
    }

    static double optimizeR(int n, int s){

        // FATAL
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < max[n]; i++) {
            min = Math.min(min, optimizeR(n+1,0000 ));
        }
        return min;
    }


}

/*
 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 */