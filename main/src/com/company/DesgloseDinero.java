package com.company;

public class DesgloseDinero {

    static int[] monedes = {1,2,5,10,20, 50};
    static int N = 9;

    public static void main(String[] args) {
        System.out.println(ilimitades(monedes, N));
    }

    static int ilimitades(int[] monedes, int N){
        double[] DP = new double[N+1];
        int[][] desglose = new int[N+1][];

        for (int i = 1; i < N+1; i++) {
            double min = Double.POSITIVE_INFINITY;
            for (int j = 0; j < monedes.length; j++) {
                if(i-monedes[j] >= 0 && DP[i-monedes[j]]+1 < min){
                    min = DP[i-monedes[j]]+1;
                    desglose[i] = new int[]{i-monedes[j], monedes[j]};
                }
            }
            DP[i] = min;
        }

        System.out.println("<DESGLOSE>");
        int i = N;
        while(i!=0){
            int iAux = i;

            i = desglose[i][0];

            System.out.println("Moneda: " + desglose[iAux][1]);
        }

        System.out.println("</DESGLOSE>");
        return (int) DP[N];
    }
}
