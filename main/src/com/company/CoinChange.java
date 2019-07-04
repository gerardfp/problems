package com.company;

public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2};
        int n = 6;

        System.out.println(getWaysDynamicArray(n, coins));
    }

    static long getWaysDynamicArray(int n, int[] c) {
        int[] K = new int[n + 1];

        for (int i = 1; i <= c.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (c[i - 1] == j) {
                    K[j] += 1;
                }
                if (j - c[i - 1] >= 0) {
                    K[j] += K[j - c[i - 1]];
                }
            }
        }

        return K[n];
    }

    public static void main2(String[] args) {
        long[] coins = {1,2};
        long n = 6;
        int[] pick = new int[coins.length];

//        System.out.println(getWaysAndPick(n,0,coins,pick));
        System.out.println("*******");
        for (int i = 0; i < 10; i++) {
            System.out.println(getWaysDynamic(i,coins));
        }
    }

    static long getWaysDynamic(long n, long[] c){
        long[][] K = new long[c.length+1][(int)n+1];

        for (int i = 1; i <= c.length ; i++) {
            for (int j = 1; j <= n; j++) {
                if(c[i-1] == j){
                    K[i][j] = 1;
                }
                if(j-c[i-1] >= 0){
                    K[i][j] += K[i][j-(int)c[i-1]];
                }
                K[i][j] += K[i-1][j];
            }
        }

        return K[c.length][(int)n];
    }



    static int getWays(long n, int m, long[] c){
        if(n==0) return 1;
        if(n<0) return 0;

        int ways = 0;
        for (int i = m; i < c.length; i++) {
            if (c[i] <= n) {
                ways += getWays(n - c[i], i, c);
            }
        }
        return ways;
    }

    static int getWaysAndPick(long n, int m, long[] c, int[] p){
        if(n==0){
            Utils.printArray(p);
            return 1;
        }
        if(n<0) return 0;

        int ways = 0;
        for (int i = m; i < c.length; i++) {
            if (c[i] <= n) {
                p[i]++;
                ways += getWaysAndPick(n - c[i], i, c, p);
                p[i]--;
            }
        }
        return ways;
    }
}
