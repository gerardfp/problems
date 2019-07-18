package com.company;

public class Amortizacion {

    static int[] valor = {7000,6000,4000,3000,2000,1000};
    static int[] coste = {300, 500, 800, 1200, 1600, 2200};

    static int nou = 10000;

    public static void main(String[] args) {
        System.out.println(rec(0, 0));
    }

    static int rec(int m, int a){
        if(m >= valor.length) return 0;
        return Math.min(rec(m+1, a+1)+coste[a], rec(m+1, 0)+10000-valor[a]+coste[0]);
    }

    static int min(){
        return 0;
    }
}
