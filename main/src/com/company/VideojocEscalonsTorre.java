package com.company;

public class VideojocEscalonsTorre {

    static int minCost(int[] p, int[] s){
        int[] K = new int[p.length+1];

        for (int i = 1; i < p.length+1; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < s.length && j<i; j++) {
                min = Math.min(min, K[i-j-1] + p[i-1] + s[j]);
            }

            K[i] = min;
            Util.printArray(K);
        }
        return K[p.length];
    }

    public static void main(String[] args) {
        int[] p = {10,2,10,2,1};
        int[] s = {2,5,6};

        System.out.println(minCost(p, s));
    }
}
/*
En un videojuego el jugador debe subir los n escalones de una torre.
Si utiliza el escalón i el jugador pierde p[i] puntos, para i = 1, 2, ..., n.
Para avanzar el jugador puede escoger entre tres tipos de movimientos:
 * paso normal, que le hace subir un escalón y le hace perder s[1] puntos;
 * salto corto, que le lleva al escalón situado 3 niveles más arriba y le hace perder s[2] puntos;
 * o salto largo, que le lleva al escalón situado 3 niveles más arriba y le hace perder s[3] puntos.
No puede retroceder.

Necesitamos un algoritmo que tenga como entrada n, p y s y cuya salida sea la cantidad total de puntos que se
pierden al recorrer la escalera desde el escalón 1 hasta el escalón n,
ambos inclusive, utilizando un recorrido que haga que esa cantidad sea mínima.
 */