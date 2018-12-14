package com.company.backtracking;

import com.company.Util;

public class NReinas {

    static boolean[][] T = new boolean[8][8];

    static boolean reinas(int j, int n){
        if(n==0){
            Util.printMatrix(T);
        }
        if(j>=T.length) return false;


        for (int i = 0; i < T.length; i++) {
            T[i][j] = true;
            if(!amenaza(i,j)) {
                reinas(j + 1, n - 1);
            }
            T[i][j] = false;
        }
        return false;
    }

    static boolean amenaza(int i, int j){
        for (int k = 1; k < T.length; k++) {
            if(hayReina(i-k,j-k)) return true;
            if(hayReina(i-k,j)) return true;
            if(hayReina(i-k,j+k)) return true;
            if(hayReina(i,j-k)) return true;
            if(hayReina(i,j+k)) return true;
            if(hayReina(i+k,j-k)) return true;
            if(hayReina(i+k,j)) return true;
            if(hayReina(i+k,j+k)) return true;
        }
        return false;
    }

    static boolean hayReina(int i, int j){
        if(i>=0 && i<T.length && j>=0 && j<T.length && T[i][j]) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(reinas(0,8));
    }
}

/*
 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 */