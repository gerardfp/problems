package com.company.backtracking;

import java.util.Scanner;

public class Sudoku4 {
    static int n = 25;
    static int[][] s = new int[n][n];
    static int nsols = 0;

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
//        print();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                s[i][j] = scanner.nextInt();
//            }
//        }

        solve();
        System.out.println(nsols);
    }

    static boolean solve(){
        int f=-1, c=-1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(s[i][j] == 0){
                    f=i;
                    c=j;
                }
            }
        }
        if(f==-1){
            nsols++;
            print();
            return true;
        }

        for (int k = 1; k <= n; k++) {
            if(isSafe(f,c,k)){
                s[f][c] = k;
                solve();
                s[f][c] = 0;
            }
        }
        return false;
    }

    static boolean isSafe(int row, int col,int num) {
        for (int d = 0; d < s.length; d++)
            if (s[row][d] == num) return false;

        for (int r = 0; r < s.length; r++)
            if (s[r][col] == num) return false;

        int sqrt = (int) Math.sqrt(s.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++)
            for (int d = boxColStart; d < boxColStart + sqrt; d++)
                if (s[r][d] == num) return false;

        return true;
    }

    static void print(){
        System.out.println("-------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }
}

/*
3 0 0  1 0 0  0 2 0
0 0 2  0 3 0  0 1 0
0 0 1  0 2 0  0 0 3

0 2 0  3 1 0  0 0 0
0 3 0  2 0 0  1 0 0
1 4 0  0 0 0  2 3 0

0 0 3  0 0 1  0 4 2
0 0 0  0 4 2  3 0 1
2 1 4  0 0 3  0 0 0

3 0 5 1 0 0 6 2 4
4 0 2 0 3 6 5 1 0
0 6 1 4 2 5 0 0 3
5 2 6 3 1 0 4 0 0
0 3 0 2 6 4 1 5 0
1 4 0 0 5 0 2 3 6
0 5 3 6 0 1 0 4 2
6 0 0 5 4 2 3 0 1
2 1 4 0 0 3 0 6 5

 */