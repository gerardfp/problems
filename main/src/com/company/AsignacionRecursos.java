package com.company;

public class AsignacionRecursos {

    static int maxBeneficio(int[][] v, int[] lm, int[] li, int R){
        double[][] K = new double[lm.length+1][R+1];

        for (int i = 1; i <R+1 ; i++) {
            K[0][i] = Double.NEGATIVE_INFINITY;
        }

        for (int i = 1; i < lm.length+1; i++) {
            for (int j = 0; j < R+1 ; j++) {
                double max = Double.NEGATIVE_INFINITY;
                for (int k = 0; k < j; k++) {
                    if(j-k >= li[i-1] && j-k <= lm[i-1]){
                        max = Math.max(max, K[i - 1][k] + v(i - 1, j - k, v));
                    }
                }
                K[i][j] = max;
                Util.printMatrix(K);
            }
        }

        return 0;
    }

    static int v(int i, int j, int[][] v){
        if(i<v.length){
            if(j<v[i].length){
                return v[i][j];
            }
            return v[i][v[i].length-1];
        }
        return -999;
    }

    public static void main(String[] args) {

        int[] lm = {3, 4, 4};
        int[] li = {2, 1, 2};

        int[][] v = {
                {0, 2, 3, 4},
                {0, 1, 3, 5},
                {0, 1, 1, 2},
        };

        System.out.println(maxBeneficio(v, lm, li, 6));
    }
}

/*
Disponemos de R unidades de un recurso y deseamos asignar cierta cantidad del mismo a cada una de D actividades distintas.
Una función v: NxN->R nos indica el beneficio que obtenemos al asignar r unidades del recurso a la actividad d con v(d,r).
Las unidades del recurso que se asignan a una actividad d debe ser inferior o igual a lm[d], y superior o igual a li[d].
Tenemos la obliagción de consumir totad las unidades disponibles del recurso R.
Deseamos obtener la asignacion de recursos a actividades que maximiza el beneficio total obtenido.
 */