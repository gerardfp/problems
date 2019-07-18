package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesgloseDinero {

    static int[] monedes = {3,5};
    static int N = 6;

    public static void main(String[] args) {
        List<Integer> desglose = minimMonedes(monedes, N);

        System.out.println("Nombre de monedes: " + desglose.size());
        System.out.println(Arrays.toString(desglose.toArray()));
    }

    static List<Integer> minimMonedes(int[] monedes, int N){
        double[] DP = new double[N+1];
        int[][] path = new int[N+1][];

        for (int i = 1; i < N+1; i++) {
            double min = Double.POSITIVE_INFINITY;
            for (int j = 0; j < monedes.length; j++) {
                if(i-monedes[j] >= 0 && DP[i-monedes[j]]+1 < min){
                    min = DP[i-monedes[j]]+1;
                    path[i] = new int[]{i-monedes[j], monedes[j]};
                }
            }
            DP[i] = min;
        }

        List<Integer> desglose = new ArrayList<>();
        int i = N;
        while(i!=0){
            int iAux = i;

            i = path[i][0];

            desglose.add(path[iAux][1]);
        }
        return desglose;
    }
}
