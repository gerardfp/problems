package com.company;

import java.util.Arrays;

public class EconomicLotSize {
    static int[] d = {3,4,2,1,3};   // demanda
    static int[] max;
    static int[] ca = {0,2,1,3,2};    // cost magatzem
    static int[] cp = {4,5,4,3,5};  // cost produccio

    public static void main(String[] args) {

        max = new int[d.length];
        max[d.length-1] = d[d.length-1];
        for (int i = d.length-2; i >= 0; i--) {
            max[i] = max[i+1] + d[i];
        }

        System.out.println(Arrays.toString(max));
        System.out.println(optimizeR(0, 0));
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