package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CuerdaCometa {

    //    static Scanner sc = new Scanner(System.in);
    static Scanner sc;

    static {
        try {
            sc = new Scanner(new File("p31"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while(sc.hasNextInt()){
            int N = sc.nextInt();
            int L = sc.nextInt();

            int[] lengs = new int[N];
            int[] costs = new int[N];

            for (int i = 0; i < N; i++) {
                lengs[i] = sc.nextInt();
                costs[i] = sc.nextInt();
            }

            System.out.println(cuerdaCometaDynamic(lengs, costs, N, L));
            System.out.println(cuerdaCometaRecursiu(lengs, costs, N, L));
            System.out.println("----------");
        }
    }

    static String cuerdaCometaDynamic(int[] lengs, int[] costs, int N, int L){
        long numPosibles = numPosiblesDynamic(lengs, N, L);
        if(numPosibles <= 0){
            return "NO";
        } else {
            long[] cc = minCordelesYCosteDynamic(lengs, costs, N, L);
            return "SI " + numPosibles + " " + cc[0] + " " + cc[1];
        }
    }

    static String cuerdaCometaRecursiu(int[] lengs, int[] costs, int N, int L){
        long numPosibles = numPosiblesRecursiu(lengs, 0, N, L);
        if(numPosibles <= 0){
            return "NO";
        } else {
            long[] cc = minCordelesYCosteRecursiu(lengs, costs, 0, N, L);
            return "SI " + numPosibles + " " + cc[0] + " " + cc[1];
        }
    }

    static long numPosiblesDynamic(int[] lengs, int n, int L){
        long[][] K = new long[n+1][L+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= L; j++) {
                if (lengs[i-1] == j) {
                    K[i][j] += 1;
                    K[i][j] += K[i-1][j];
                } else {
                    K[i][j] += K[i - 1][j];
                    if (lengs[i - 1] <= j) {
                        K[i][j] += K[i - 1][j - lengs[i - 1]];
                    }
                }
            }
        }
        return K[n][L];
    }

    static long numPosiblesRecursiu(int[] a, int i, int n, int L){
        if(i == n){
            return 0;
        }

        if(a[i] == L){
            return 1 + numPosiblesRecursiu(a, i + 1, n, L);
        } else if(a[i] > L) {
            return numPosiblesRecursiu(a, i + 1, n, L);
        } else {
            return numPosiblesRecursiu(a, i + 1, n, L - a[i]) + numPosiblesRecursiu(a, i + 1, n, L);
        }
    }


    static long[] minCordelesYCosteDynamic(int[] lengs, int[] costs, int n, int L){
        long[][] Kcord = new long[n+1][L+1];
        long[][] Kcost = new long[n+1][L+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= L; j++) {
                if(lengs[i-1] == j){
                    Kcord[i][j] = 1;
                    Kcost[i][j] = costs[i-1];
                } else {
                    long minCordeles1 = 0;
                    long minCoste1 = 0;
                    if (j - lengs[i - 1] >= 0) {
                        minCordeles1 = Kcord[i - 1][j - lengs[i - 1]];
                        minCoste1 = Kcost[i-1][j-lengs[i-1]];
                        if (minCordeles1 != 0) {
                            minCordeles1 += 1;
                            minCoste1 += costs[i-1];
                        }
                    }

                    long minCordeles2 = Kcord[i - 1][j];
                    long minCoste2 = Kcost[i-1][j];

                    if(minCordeles1 != 0 && minCordeles2 !=0){
                        Kcord[i][j] = Math.min(minCordeles1, minCordeles2);
                        Kcost[i][j] = Math.min(minCoste1, minCoste2);
                    } else if(minCordeles2 != 0){
                        Kcord[i][j] = minCordeles2;
                        Kcost[i][j] = minCoste2;
                    } else if(minCordeles1 != 0) {
                        Kcord[i][j] = minCordeles1;
                        Kcost[i][j] = minCoste1;
                    }
                }
            }
        }
        return new long[]{Kcord[n][L], Kcost[n][L]};
    }


    static long[] minCordelesYCosteRecursiu(int[] a, int[] c, int i, int n, int L){
        if(i == n){
            return new long[]{Long.MAX_VALUE, Long.MAX_VALUE};
        }

        if(a[i] == L){
            return new long[]{1, Math.min(c[i], minCordelesYCosteRecursiu(a, c, i + 1, n, L)[1])};
        } else if(a[i] > L) {
            return minCordelesYCosteRecursiu(a, c, i + 1, n, L);
        } else {
            long[] val1 = minCordelesYCosteRecursiu(a, c, i + 1, n, L - a[i]);
            long[] val2 = minCordelesYCosteRecursiu(a, c, i + 1, n, L);
            return new long[]{ Math.min(sum(val1[0],  1), val2[0]), Math.min(sum(val1[1], c[i]), val2[1])};
        }
    }

    static long minCordelesRecursiu(int[] a, int i, int n, int L){
        if(i == n){
            return Long.MAX_VALUE;
        }

        if(a[i] == L){
            return 1;
        } else if(a[i] > L) {
            return minCordelesRecursiu(a, i + 1, n, L);
        } else {
            long min1 = minCordelesRecursiu(a, i + 1, n, L - a[i]);
            long min2 = minCordelesRecursiu(a, i + 1, n, L);
            return Math.min(sum(min1,  1), min2);
        }
    }

    static long minCosteRecursiu(int[] a, int[] c, int i, int n, int L){
        if(i == n){
            return Long.MAX_VALUE;
        }

        if(a[i] == L){
            return Math.min(c[i], minCosteRecursiu(a, c, i + 1, n, L));
        } else if(a[i] > L) {
            return minCosteRecursiu(a, c, i + 1, n, L);
        } else {
            long min1 = minCosteRecursiu(a, c, i + 1, n, L - a[i]);
            long min2 = minCosteRecursiu(a, c, i + 1, n, L);
            return Math.min(sum(min1, c[i]), min2);
        }
    }

    static long sum(long a, long b){
        return a == Long.MAX_VALUE || b == Long.MAX_VALUE ? Long.MAX_VALUE : a+b;
    }
}
