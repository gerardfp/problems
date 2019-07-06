package com.company;

/*
Una importante empresa de inversiones ha dise˜nado una prueba para los aspirantes a trabajar en ella: les asigna
una cantidad de dinero y, durante T meses, deben hacer un total de M inversiones de dicha cantidad con el
objetivo de obtener el maximo beneficio. Para ello, pueden invertir el dinero a plazo fijo en N diferentes productos
financieros. Cada producto i (1 ≤ i ≤ N) tiene asociado el plazo de recuperacion d(i) y el beneficio b(i). El
procedimiento de inversion consiste en seleccionar un producto i, invertir el capital completo en el y, transcurrido
el tiempo d(i), recoger el beneficio y repetir el proceso. No hay inconveniente en invertir en un mismo producto
mas de una vez. Durante todo el periodo T, el capital debe estar invertido en algun producto y, una vez finalizado
el periodo, hay que haber recuperado el capital inicial.
Deseas saber en que productos realizaras las inversiones para obtener el maximo beneficio posible.
 */

import java.util.Arrays;

public class Inversiones {

    static int[] d = {1,3,5,2};
    static int[] b = {10,33,57,19};

    public static void main(String[] args) {
        System.out.println(max(6,4));
    }

    static int max(int T, int M){
        double[][] DP = new double[M+1][T+1];
        int[][][] path = new int[M+1][T+1][];

        for (int i = 1; i < M+1; i++) {
            DP[i][0] = Double.NEGATIVE_INFINITY;
        }
        for (int i = 1; i < T+1; i++) {
            DP[0][i] = Double.NEGATIVE_INFINITY;
        }

        for (int i = 1; i < M+1; i++) {
            for (int j = 1; j < T+1; j++) {
                double max = Double.NEGATIVE_INFINITY;
                for (int k = 0; k < d.length; k++) {
                    if(j-d[k] >= 0)
                        if(DP[i-1][j-d[k]]+b[k] > max){
                            max = DP[i-1][j-d[k]]+b[k];
                            path[i][j] = new int[]{i-1, j-d[k], k};
                        }
                }
                DP[i][j] = max;
                Util.printMatrix(DP);
            }
        }


        System.out.println("<PATH>");
        int i=M, j=T;
        while(i !=0 && j !=0){
            int iAux = i;
            int jAux = j;

            i = path[i][j][0];
            j = path[iAux][j][1];

            System.out.println("Producte: " + path[iAux][jAux][2]);
        }
        System.out.println("</PATH>");

        return (int) DP[M][T];
    }
}
