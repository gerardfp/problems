package com.company;

/*
Disponemos de R unidades de un recurso y deseamos asignar cierta cantidad del mismo a cada una de D actividades distintas.
Una función v: NxN->R nos indica el beneficio que obtenemos al asignar r unidades del recurso a la actividad d con v(d,r).
Las unidades del recurso que se asignan a una actividad d debe ser inferior o igual a lm[d], y superior o igual a li[d].
Tenemos la obliagción de consumir totad las unidades disponibles del recurso R.
Deseamos obtener la asignacion de recursos a actividades que maximiza el beneficio total obtenido.
 */

public class AsignacionRecursos {

    public static void main(String[] args) {

        int[] lm = {3, 4, 4};   // limit superior
        int[] li = {2, 1, 2};   // limit inferior

        int[][] v = {           // benefici
                {0, 2, 3, 4, 5},
                {0, 1, 3, 5, 6},
                {0, 1, 1, 2, 5},
        };

        System.out.println(maxBenefici(v, lm, li, 6));
    }

    private static int maxBenefici(int[][] v, int[] lm, int[] li, int R) {
        double[][] DP = new double[lm.length+1][R+1];

        for (int i = 1; i < R+1; i++)
            DP[0][i] = Double.NEGATIVE_INFINITY;

        for (int i = 1; i < lm.length+1; i++)
            DP[i][0] = Double.NEGATIVE_INFINITY;

        for (int i = 1; i < lm.length+1; i++) {
            for (int j = 1; j < R + 1; j++) {
                double max = Double.NEGATIVE_INFINITY;
                for (int k = li[i - 1]; k <= lm[i - 1]; k++)
                    if (j - k >= 0)
                        max = Math.max(max, DP[i - 1][j - k] + v[i - 1][k]);
                DP[i][j] = max;
            }
        }

        return (int) DP[lm.length][R];
    }
}