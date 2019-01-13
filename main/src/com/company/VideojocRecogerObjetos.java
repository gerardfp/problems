package com.company;

public class VideojocRecogerObjetos {

    static int minObjects(int[] p, int v){
        int[] K = new int[v+1];

        for (int i = 1; i <= v; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < p.length && p[j] <= i; j++) {
                min = Math.min(min, K[i-p[j]]+1);
            }
            K[i] = min;
        }
        return K[v];
    }

    static double minObjectsRecursiu(int[] p, int v0, int v){
        if (v0 == v) return 0;
        if (v0 > v) return Double.POSITIVE_INFINITY;

        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < p.length; i++) {
            min = Math.min(min, minObjectsRecursiu(p, p[i]+v0, v) + 1);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] p = {1,3,4,7,8, 20};

        System.out.println(minObjects(p, 23));
        System.out.println(minObjectsRecursiu(p, 0, 23));
    }
}

/* http://www3.uji.es/~vjimenez/AULASVIRTUALES/AED-1718/#ejercicios53  [18]
 * En un videojuego el jugador puede recoger n tipos de objetos. Cada tipo de objeto le proporciona una determinada
 * cantidad de puntos, de tipo entero. Sea p un vector de talla n con dichas cantidades. El objetivo del jugador es
 * acumular exactamente una cantidad preestablecida v de puntos. La cantidad disponible de objetos de cada tipo es
 * ilimitada. Existe un tipo de objeto que proporciona solamente un punto, lo que garantiza que el objetivo siempre
 * se puede conseguir. Necesitamos un algoritmo que, dados p y v, calcule la mínima cantidad de objetos que necesita
 * el jugador para sumar v puntos.
 */
