package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

/*
https://www.aceptaelreto.com/problem/statement.php?id=395
 */
public class CakeCutting {

    static char[][] tarta;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int rows = sc.nextInt();
            int cols = sc.nextInt();
            sc.nextLine();

            tarta = new char[rows][cols];
            for (int i = 0; i < rows; i++)
                tarta[i] = sc.nextLine().toCharArray();

            System.out.println(minCortes(0, rows, 0, cols));
        }
    }

    static int minCortes(int i0, int i1, int j0, int j1) {

        if (areEqual(i0, i1, j0, j1))
            return 0;

        int mini = Integer.MAX_VALUE;
        int minj = Integer.MAX_VALUE;

        for (int i = i0 + 1; i < i1; i++)
            mini = Math.min(mini, minCortes(i0, i, j0, j1) + minCortes(i, i1, j0, j1));

        for (int j = j0 + 1; j < j1; j++)
            minj = Math.min(minj, minCortes(i0, i1, j0, j) + minCortes(i0, i1, j, j1));

        int min = Math.min(mini, minj) + 1;

        return min;
    }

    static boolean areEqual(int i0, int i1, int j0, int j1) {

        for (int i = i0; i < i1; i++)
            for (int j = j0; j < j1; j++)
                if (tarta[i][j] != tarta[i0][j0])
                    return false;

        return true;
    }
}
/*
    static int minCortesDynamic(int i0, int i1, int j0, int j1){

        int[][][][] K = new int[i1+1][i1+1][j1+1][j1+1];

        for (int m = 1; m <= i1; m++) {
            for (int i = 0; i < i1 - m + 1; i++) {
                for (int k = 1; k <= j1; k++) {
                    for (int j = 0; j < j1 - k + 1; j++) {
                        System.out.println(i + " " + (i+m) + " " +j + " " + (j+k));
                        if(areEqual(i,i+m,j,j+k)){
                            K[i][i+m][j][j+k] = 0;
                        } else {
                            int min = Integer.MAX_VALUE;
                            for (int ii = 1; ii < m; ii++) {
                                if (K[i][i+ii][j][j+k] + K[i+ii][i+m][j][j+k] < min) {
                                    min = K[i][i+ii][j][j+k] + K[i+ii][i+m][j][j+k];
                                }
                            }
                            for (int jj = 1; jj < k; jj++) {
                                if (K[i][i+m][j][j+jj] + K[i][i+m][j+jj][j+k] < min) {
                                    min = K[i][i+m][j][j+jj] + K[i][i+m][j+jj][j+k];
                                }
                            }
                            K[i][i+m][j][j+k] = min + 1;
                        }
                    }
                }
            }
        }

        return K[0][i1][0][j1];
    }



    static int minCortesMemoization(int i0, int i1, int j0, int j1){

        if(memo[i0][i1][j0][j1] != -1){
            return memo[i0][i1][j0][j1];
        }

        if(areEqual(i0, i1, j0, j1)){
            return 0;
        }

        int mini = Integer.MAX_VALUE;
        int minj = Integer.MAX_VALUE;
        for (int i = i0+1; i < i1; i++) {
            mini = Math.min(mini, minCortesMemoization(i0, i, j0, j1) + minCortesMemoization(i, i1, j0, j1));
        }
        for (int j = j0+1; j < j1; j++) {
            minj = Math.min(minj, minCortesMemoization(i0, i1, j0, j) + minCortesMemoization(i0, i1, j, j1));
        }
        int min = Math.min(mini, minj) + 1;

        memo[i0][i1][j0][j1] = min;

        return min;
    }


}
*/