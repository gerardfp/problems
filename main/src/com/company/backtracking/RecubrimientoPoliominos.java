package com.company.backtracking;


import com.company.Util;

import java.util.ArrayList;
import java.util.List;

public class RecubrimientoPoliominos {

    static boolean[][] T = new boolean[3][3];
    static int Psize = 4;

    static boolean[][] P = new boolean[Psize][Psize];
    static int Pcurrentsize = 0;

    static Poliominos poliominos = new Poliominos();

    static boolean generarPoliominos(int n){

        if(n==0 && Pcurrentsize == Psize){
            poliominos.add(new Poliomino(P));
            return true;
        }

        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[0].length; j++) {
                if(!get(i,j)) {
                    P[i][j] = true;
                    Pcurrentsize++;
                    if(isValid(i,j)) {
                        generarPoliominos(n - 1);
                    }
                    P[i][j] = false;
                    Pcurrentsize--;
                }
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
        if(i>=0 && i<P.length && j>=0 && j<P[0].length && P[i][j]) return true;
        return false;
    }

    static class Poliomino {
        boolean[][] P;
        char[][] Pch;

        Poliomino(boolean[][] P){
            int w = 0, h = 0;
            int sw = P.length, ew = 0, sh = P[0].length, eh = 0;
            for (int i = 0; i < P.length; i++) {
                for (int j = 0; j < P[i].length; j++) {
                    if(P[i][j]){
                        if(j<sw) sw=j;
                        if(j>ew) ew=j;
                        if(i<sh) sh=i;
                        if(i>eh) eh=i;

                    }
                }
            }

            this.P = new boolean[eh-sh+1][ew-sw+1];
            this.Pch = new char[eh-sh+1][ew-sw+1];

            for (int i = sh; i <= eh; i++) {
                for (int j = sw; j <= ew ; j++) {
                    this.P[i-sh][j-sw] = P[i][j];
                }
            }
        }

        boolean isEqual(Poliomino poliomino){
            if(this.P.length != poliomino.P.length || this.P[0].length != poliomino.P[0].length) return false;

            for (int i = 0; i < P.length; i++) {
                for (int j = 0; j < P[0].length; j++) {
                    if(this.P[i][j] != poliomino.P[i][j]) return false;
                }
            }
            return true;
        }

        void setLetra(char letra){
            for (int i = 0; i < P.length; i++) {
                for (int j = 0; j < P[0].length; j++) {
                    if(P[i][j]) Pch[i][j] = letra;
                    else Pch[i][j] = ' ';
                }
            }

        }

    }

    static class Poliominos {
        List<Poliomino> poliominos = new ArrayList<>();

        void add(Poliomino poliomino){
            boolean esta = false;
            for (int i = 0; i < poliominos.size(); i++) {
                if(poliomino.isEqual(poliominos.get(i))){
                    esta = true;
                }
            }
            if(!esta){
                poliomino.setLetra((char) (poliominos.size()+65));
                poliominos.add(poliomino);
            }

        }
    }

    static char[][] tileBoard(List<Poliomino> poliominos, char[][] board){

        for (Poliomino poliomino: poliominos){
            if (fits(poliomino, board, 0,0)){
                put(poliomino, board, 0,0);
                Util.printMatrix(board);
                remove(poliomino, board, 0,0);
            }
        }

        return board;
    }

    static boolean fits(Poliomino poliomino, char[][] board, int i, int j){
        if(poliomino.P.length+i > board.length || poliomino.P[0].length + j > board[0].length)
            return false;

        for (int k = 0; k < poliomino.P.length; k++) {
            for (int l = 0; l < poliomino.P[0].length; l++) {
                if(!poliomino.P[k][l] && board[k+i][l+j] != '·') return false;
            }
        }

        return true;
    }

    static void put(Poliomino poliomino, char[][] board, int i, int j){
        for (int k = 0; k < poliomino.P.length; k++) {
            for (int l = 0; l < poliomino.P[0].length; l++) {
                if(poliomino.P[k][l])
                    board[k+i][l+j] = poliomino.Pch[k][l];
            }
        }
    }

    static void remove(Poliomino poliomino, char[][] board, int i, int j){
        for (int k = 0; k < poliomino.P.length; k++) {
            for (int l = 0; l < poliomino.P[0].length; l++) {
                if(poliomino.P[k][l])
                    board[k+i][l+j] = '.';
            }
        }
    }


    public static void main(String[] args) {
        generarPoliominos(Psize);

        System.out.println(poliominos.poliominos.size());
        for (Poliomino poliomino: poliominos.poliominos){
            Util.printMatrix(poliomino.Pch);
        }

        char[][] board = {
                {'·','·','·','·'},
                {' ','·','·','·'},
                {'·','·','·','·'},
        };

        board = tileBoard(poliominos.poliominos, board);

        Util.printMatrix(board);
    }
}