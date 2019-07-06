package com.company;

/*
Deseamos colocar una valla de M metros en l´ınea recta para marcar el linde entre dos terrenos. La construccion de
la valla la haremos ensamblando, una tras otra, piezas de valla prefabricadas que podemos adquirir en promocion
en una gran superficie. Hay piezas disponibles de N longitudes distintas. De cada tipo de pieza i (1 ≤ i ≤ N)
conocemos su longitud, l(i), su precio, p(i), y el numero de unidades que quedan en stock, s(i). La promocion
indica que al hacer una compra de piezas estamos obligados a adquirir un mınimo de una pieza de cada tipo, por
lo que ya sabemos que al menos deberemos emplear una de cada en la construcci´on de la valla.
Deseamos conocer qu´e piezas hemos de comprar para construir una valla de longitud M con el menor coste
posible.
 */

public class Valla {
    static int[] l = {1,2,3};
    static int[] p = {2,3,4};
    static int[] s = {4,3,1};

    public static void main(String[] args) {
        for (int i = 7; i <=7 ; i++) {
            System.out.println(min(l,p,s,i));
            System.out.println("----");
        }
    }

    static int min(int[] l, int[] p, int[] s, int M){
        double[][] K = new double[p.length+1][M+1];
        int[][][] path = new int[p.length+1][M+1][];

        for (int i = 1; i < M+1; i++) {
            K[0][i] = Double.POSITIVE_INFINITY;
        }
        for (int i = 1; i < p.length+1; i++) {
            K[i][0] = Double.POSITIVE_INFINITY;
        }

        for (int i = 1; i < p.length+1; i++) {
            for (int j = 1; j < M+1; j++) {
                double min = Double.POSITIVE_INFINITY;

                for (int k = 1; k <= s[i-1]; k++)
                    if(j >= l[i-1]*k && K[i-1][j-l[i-1]*k]+p[i-1]*k < min) {
                        min = K[i - 1][j - l[i - 1] * k] + p[i - 1] * k;
                        path[i][j] = new int[]{i-1, j-l[i-1]*k, k};
                    }

                K[i][j] = min;
            }
        }

        System.out.println("<PATH>");
        int i = p.length;
        int j = M;

        while(j != 0){
            int iAux = i;
            int jAux = j;

            i = path[i][j][0];
            j = path[iAux][j][1];

            System.out.println("Valla: " + path[iAux][jAux][0] + "  uds: " + path[iAux][jAux][2]);
        }

        System.out.println("</PATH>");

        return (int) K[p.length][M];
    }
}