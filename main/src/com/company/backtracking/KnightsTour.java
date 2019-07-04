package com.company.backtracking;

import com.company.Utils;

public class KnightsTour {

    static int f=0, c=0;
    static int[][] board = new int[8][8];
    static int[][] moves = {{-2, -1}, {-2, 1}, {2, -1},{2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    public static void main(String[] args) {
	    board[0][0] = 1;
        knightsTour(0, 0, 1);
        Utils.printMatrix(board);
    }

    static boolean knightsTour(int f, int c, int n){
        if(n==64) return true;

        boolean isSolution = true;
        for (int i = 0; i < moves.length; i++) {
            if(free(f+moves[i][0], c+moves[i][1])){
                board[f+moves[i][0]][c+moves[i][1]] = n+1;
                boolean valid = knightsTour(f+moves[i][0], c+moves[i][1], n+1);
                if(!valid){
                    board[f+moves[i][0]][c+moves[i][1]] = 0;
                }
                isSolution &= valid;
            }
        }
        return isSolution;
    }

    static boolean free(int f, int c) {
        if (f < 0 || c < 0 || f > 7 || c > 7) return false;
        return board[f][c] == 0;
    }
}
