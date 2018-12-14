package com.company.backtracking;


import com.company.Util;
import com.sun.scenario.effect.impl.prism.ps.PPSZeroSamplerPeer;

import java.util.List;

public class Poliominos {

    static boolean[][] T = new boolean[8][8];
    static int Psize = 4;

    static List<boolean[][]> Ps;
    static boolean[][] P = new boolean[Psize][Psize-1];
    static int Pcurrentsize = 0;

    static boolean poliominos(int n){

        if(n==0){
            Util.printMatrix(P);
        }

        for (int i = 0; i < Psize; i++) {
            for (int j = 0; j < Psize-1; j++) {

                P[i][j] = true;
                Pcurrentsize++;
                if (isValid(i, j)) {
                    System.out.println("isValid");
                    Util.printMatrix(P);
                    poliominos(n - 1);
                }
                P[i][j] = false;
                Pcurrentsize--;
            }
        }
        return false;
    }

    static boolean isValid(int i, int j){
        if(Pcurrentsize == 1) return true;

        if(get(i-1,j) || get(i,j-1) || get(i,j+1) || get(i+1, j)) return true;
        return false;
    }

    static boolean get(int i, int j){
        if(i>=0 && i<P.length && j>=0 && j<P.length-1 && P[i][j]) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(poliominos( 4));
    }
}