package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Un vendedor ambulante de productos de artesan´ıa realiza una ruta a trav´es de una serie de P pueblos dispuestos
a lo largo de una carretera. Parte de la ciudad en la que habita (situada al comienzo de la carretera) y debe llegar
a una ciudad que se encuentra en el otro extremo de la carretera sin retroceder nunca. Su sistema de ventas es
siempre el mismo: un d´ıa llega a un pueblo por la ma˜nana, vende toda la mercanc´ıa, compra la artesan´ıa tarta´ıpica
del pueblo y contin´ua hasta otro pueblo, en el que, al d´ıa siguiente, repetir´a la operaci´on. Al principio del viaje
partir´a con productos tarta´ıpicos de su ciudad y al acabar deber´a vender la mercanc´ıa que le quede en la ciudad final
de trayecto. El comerciante, una vez ha parado en un pueblo, no vuelve a detenerse hasta haber atravesado un
m´ınimo de dos pueblos m´as (al ser pueblos vecinos no podr´ıa vender a buen precio los objetos).
El comerciante tiene una estimaci´on del beneficio que puede obtener en un determinado pueblo en funci´on de
la mercanc´ıa que venda en ´el. Es decir, si numeramos los pueblos entre 1 y P seg´un el orden en que aparecen en la
carretera, el beneficio que obtiene al vender la mercancia de un pueblo i en otro j ser´ıa, b(i, j), para 1 ≤ i < j ≤ P.
Se pretende averiguar en que pueblos deber´ıa parar para obtener el m´aximo beneficio posible.
 */
public class VendedorAmbulante {

    static int[][] b = {
            {0,0,2,3,5},
            {0,0,0,4,7},
            {0,0,0,0,6}
    };

    public static void main(String[] args) {
        System.out.println(max(b));
    }

    static int max(int[][] b){
        double[] K = new double[b[0].length];
        int[] path = new int[b[0].length];

        for (int i = 1; i < b[0].length; i++) {
            double max = 0;
            for (int j = 0; j < i-1; j++) {
                if(K[j] + b[j][i] > max){
                    max = K[j]+b[j][i];
                    path[i] = j;
                }
            }
            K[i] = max;
        }

        System.out.println(Arrays.toString(path));
        int e = path[path.length-1];
        System.out.println(e);
        while((e = path[path[e]]) != 0){
            System.out.println(e);
        }

        return (int) K[b[0].length-1];
    }
}