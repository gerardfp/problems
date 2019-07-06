package com.company;

import java.util.ArrayList;

public class VallaCarles {
    private final int[] longitud;
    private final int[] preu;
    private final int[] stock;

    public VallaCarles(int[] longitud, int[] preu, int[] stock) {
        this.longitud = longitud;
        this.preu = preu;
        this.stock = stock;
    }

    public static void main(String[] args) {
        int[] longitud = {1, 2, 3};
        int[] preu = {2, 3, 4};
        int[] stock = {4, 3, 1};

        VallaCarles valla = new VallaCarles(longitud, preu, stock);
        System.out.println(valla.busca(7));
    }

    private int busca(int len) {
        double val[][] = new double[longitud.length + 1][len + 1];
        int path[][][] = new int[longitud.length + 1][len + 1][2];

        for (int i = 0; i <= longitud.length; i++) {
            for (int j = 0; j <= len; j++) {
                if (i == 0 && j == 0) {
                    val[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    val[i][j] = Double.POSITIVE_INFINITY;
                } else {
                    double min = Double.POSITIVE_INFINITY;

                    for (int k = 1; k <= stock[i - 1]; k++) {
                        if (j >= longitud[i - 1]*k) {
                            double aux = preu[i - 1]*k + val[i - 1][j - longitud[i - 1]*k];

                            if (aux < min) {
                                min = aux;
                                path[i][j][0] = i-1;
                                path[i][j][1] = j - longitud[i - 1];
                            }
                        }
                    }

                    val[i][j] = min;
                }
            }
        }

        Util.printMatrix(val);

        followPath(path);

        return (int) val[longitud.length][len];
    }

    private void followPath(int[][][] path) {
        int i = path.length -1;
        int j = path[0].length -1;

        while (j!=0) {
            int iAux = i;
            int jAux = j;

            i = path[iAux][jAux][0];
            j = path[iAux][jAux][1];

            if (j != jAux) {
                System.out.printf("Producte: %d, unitats :%d\n", (iAux-1), ((jAux-j)/longitud[iAux-1]));
            }
        }
    }
}