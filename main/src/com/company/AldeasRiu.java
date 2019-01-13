package com.company;

public class AldeasRiu {

    static int minCost(int[][] c){

        int[] K = new int[c.length];

        for (int i = 1; i < c.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                min = Math.min(min, K[j] + c[j][i]);
            }
            K[i] = min;
        }

        return K[c.length-1];
    }

    static double minCostRecursiu(int[][] c, int a){
        if(a >= c.length-1) return 0;

        double min = Double.POSITIVE_INFINITY;
        for (int i = a+1; i < c.length; i++) {
            min = Math.min(min, minCostRecursiu(c, i) + c[a][i]);
        }

        return min;
    }

    public static void main(String[] args) {
        int[][] costes = {
                {0, 5, 8, 13, 18},
                {0, 0, 4, 5, 19},
                {0, 0, 0, 3, 7},
                {0, 0, 0, 0, 3},
                {0, 0, 0, 0, 0}
        };

        System.out.println(minCost(costes));
        System.out.println(minCostRecursiu(costes, 0));
    }
}

/* http://www3.uji.es/~vjimenez/AULASVIRTUALES/AED-1718/#ejercicios53  [17]

En las orillas de un rio hay n aldeas. Debemos desplazarnos rio abajo desde la primera hasta la última,
y tenemos la libertad de hacer escalas en otras aldeas o no. Para desplazarnos de una aldea a otra
podemos alquilar una barca, con la que solamente podemos navegar a favor de la corriente.
Nos dan una matriz de costes m en la que, para cada j > i, m[i][j] es el coste de coger
una barca en la aldea i y devolverla rio abajo en la aldea j.
Puede suceder que haciendo escalas intermedias el coste total sea menor, o no.
Partiendo de la información de la matriz queremos averiguar el mínimo coste para ir desde la aldea 1 hasta la n.
 */