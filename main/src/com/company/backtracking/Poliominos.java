package com.company.backtracking;


import com.company.Util;

import java.util.List;

public class Poliominos {

    static boolean[][] T = new boolean[8][8];
    static int Psize = 3;

    static List<boolean[][]> Ps;
    static boolean[][] P = new boolean[Psize][Psize-1];
    static int Pcurrentsize = 0;

    static boolean poliominos(int i0, int j0, int n){

        if(n==0){
            System.out.println("isValid");
            Util.printMatrix(P);
            return true;
        }

        if(j0 >= Psize-1) return false;
        if(i0 >= Psize) return false;


        for (int i = i0; i < Psize; i++) {
            for (int j = j0; j < Psize-1; j++) {
                if(!get(i,j)) {
                    P[i][j] = true;
                    Pcurrentsize++;
                    if (isValid(i, j)) {
                        poliominos(i + 1, j, n - 1);
                        poliominos(i, j + 1, n - 1);
                    }
                    P[i][j0] = false;
                    Pcurrentsize--;
                }
                poliominos(i+1, j, n - 1);
                poliominos(i, j+1, n - 1);


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
        System.out.println(poliominos( 0,0,Psize));
    }
}