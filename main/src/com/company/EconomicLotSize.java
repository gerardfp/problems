package com.company;

import java.util.Arrays;

public class EconomicLotSize {
    static int[] d = {3,2,3};   // demanda
    static int[] cp = {5,1,2};  // cost produccio
    static int[] ca = {1,4,0};    // cost magatzem


    public static void main(String[] args) {
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
}