package com.company.backtracking;

public class MinimaxChess {

    static char[][] whites = {
            {'N','B','2'},
            {'Q','B','1'}
    };

    static char[][] blacks = {
            {'Q','A','4'}
    };

    public static void main(String[] args) {
        simplifiedChessEngine(whites, blacks, 1);
    }

    static String simplifiedChessEngine(char[][] whites, char[][] blacks, int moves) {
        return "YES";
    }

    static boolean minimax(char[][] whites, char[][] blacks, int moves, int turn){
        char[] blackQuennPosition = blackQueenPosition(blacks);

        for (int i = 0; i < whites.length; i++) {
            if(whites[i][1] == blackQuennPosition[0] && whites[i][2] == blackQuennPosition[1]){
                return true;
            }
        }

        if(turn == 0){
            for (int i = 0; i < whites.length; i++) {

            }
        }else{

        }
        return false;
    }

    static char[] blackQueenPosition(char[][] blacks){
        for (int i = 0; i < blacks.length; i++) {
            if(blacks[i][0] == 'Q'){
                return new char[]{blacks[i][1], blacks[i][2]};
            }
        }
        return new char[]{'-','-'};
    }

    static char[] getMoves(char[][] whites, char[][] blacks, int turn, int piece){
        if(turn == 0){
            if(whites[piece][0] == 'Q'){

            }
        }

        return new char[2];
    }
}
