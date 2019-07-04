package com.company.backtracking;

import java.util.Scanner;

public class Sudoku {
    static int[][] s = new int[9][9];

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s[i][j] = 0; //scanner.nextInt();
//                System.out.println("Q="+i+"  C="+j + "  => " + (j/3+i/3*3) + " " + (i*3+j%3)%9);
            }
        }

        generate(0, 0);
        System.out.println(isValid());
    }

    static void generate(int n, int m){
        if(n>8 && m>8) return;
        print();
        scanner.nextLine();
        for (int i = n; i < 9; i++) {
            for (int j = m; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    s[i][j] = k;
                    if(isValid()){
                        generate(i,j);
                    } else {
                        s[i][j] = 0;
                    }
                }
            }

        }
    }

    static boolean isValid(){
        for (int i = 0; i < 9; i++) {
            boolean[] f = new boolean[9];
            boolean[] c = new boolean[9];
            boolean[] q = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if(s[i][j]-1 < 0 || s[j][i]-1 < 0 || s[j/3+i/3*3][(i*3+j%3)%9]-1 < 0) continue;
                if(f[s[i][j]-1] || c[s[j][i]-1] || q[s[j/3+i/3*3][(i*3+j%3)%9]-1]) return false;
                f[s[i][j]-1] = true;
                c[s[j][i]-1] = true;
                q[s[j/3+i/3*3][(i*3+j%3)%9]-1] = true;
            }
        }
        return true;
    }

    static void print(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/*
4 1 3 8 2 5 6 7 9
5 6 7 1 4 9 8 3 2
2 8 9 7 3 6 1 4 5
1 9 5 4 6 2 7 8 3
7 2 6 9 8 3 5 1 4
3 4 8 5 1 7 2 9 6
8 5 1 6 9 4 3 2 7
9 7 2 3 5 8 4 6 1
6 3 4 2 7 1 9 5 8

 */