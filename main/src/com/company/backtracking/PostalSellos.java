package com.company.backtracking;

import java.util.Arrays;

/*
Estamos en un pa´ıs exotico y queremos enviar una postal a un amigo. Para ello, hemos adquirido un lote de sellos de n
valores diferentes y que contiene 3 sellos de cada valor. En la oficina de correos nos han indicado que enviar una postal a
nuestro amigo tiene un coste C. Adem´as, nos informan de que en las postales del pa´ıs es obligatorio colocar un n´umero
exacto de sellos M, que depende del tipo de postal.
Dados los valores de los sellos del lote, (v1, v2, .. . ,vn), C y M, queremos encontrar una combinaci´on de exactamente M
sellos de coste total C, sabiendo que no podemos usar m´as de tres sellos del mismo valor.
Por ejemplo, si los valores fuesen v = [1, 2, 3, 5, 8, 10], el coste del env´ıo C = 16 y tuviese que emplear M = 5 sellos, una
posible soluci´on podr´ıa ser usar un sello de 10, dos de 2 y dos de 1. No ser´ıan soluciones v´alidas usar un sello de 10, otro de
5 y otro de 1 (no son 5 sellos), ni usar uno de 8 y cuatro de 2 (se usan m´as de tres sellos de un valor).
 */
public class PostalSellos {

    static int[] v = {1,2,3,5,8,10};
    static int C=19;
    static int M=3;
//    static int[] v = {1,2,3};
//    static int C=6;
//    static int M=2;
    static int[] combi = new int[v.length];

    public static void main(String[] args) {
        System.out.println(combi(C,M));
        System.out.println(Arrays.toString(combi));
    }

    static boolean combi(int c, int m){
        if(c==0 && m==0) return true;
        if(c<=0 || m<=0) return false;

        for (int i = 0; i < v.length; i++) {
            if(combi[i] < 3){
                combi[i]++;
                if(!combi(c-v[i], m-1))
                    combi[i]--;
                else
                    return true;
            }
        }

        return false;
    }
}
