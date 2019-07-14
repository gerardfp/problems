package com.company;

public class OptimalPath {
    static int[][] coins = {
            {1, 2, 3},
            {7, 9, 6},
            {8, 5, 4}
    };

    public static void main(String[] args) {
        System.out.println(optim(0,0,coins));
        System.out.println(optimD(coins));
    }

    private static int optim(int x, int y, int[][] coins) {
        return x >= coins.length || y >= coins.length ? 0 : Math.max(optim(x+1, y, coins), optim(x,y+1,coins))+coins[x][y];
    }

    private static int optimD(int[][] coins){
        int[][] DP = new int[coins.length][coins[0].length];

        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < coins[0].length; j++) {
                int max = 0;
                if(i-1 >= 0){
                    max = Math.max(max,DP[i-1][j]);
                }
                if(j-1 >=0){
                    max = Math.max(max, DP[i][j-1]);
                }
                DP[i][j] = max+coins[i][j];
            }
        }

        return DP[coins.length-1][coins[0].length-1];
    }
}
