package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Riu {

  private final int[][] posicions;

  public Riu(int[][] posicions) {
    this.posicions = posicions;
  }


  public static void main(String[] args) {
    int inf = Integer.MAX_VALUE;
    int[][] posicions = {
            {inf, 5, 8, 13},
            {inf, inf, 4, 5},
            {inf, inf, inf, 3},
            {inf, inf, inf, inf}
    };

    Riu riu = new Riu(posicions);
    System.out.println(riu.minCost());
  }

  private int minCost() {
    int[][] val = new int[posicions.length + 1][posicions.length + 1];

    for (int i = 0; i <= posicions.length; i++) {
      val[i][0] = Integer.MAX_VALUE;
    }

    for (int i = 2; i <= posicions.length; i++) {
      for (int j = 1; j <= posicions.length; j++) {

        int noEntra = val[i][j - 1];
        int entra = posicions[j - 1][i - 1] + val[j][posicions.length];


        val[i][j] = Math.min(entra, noEntra);

        printMatrix(val);
      }
    }
    return val[posicions.length][posicions.length];
  }

  private void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(" " + matrix[j][i]);
      }
      System.out.println();
    }
    System.out.println("---");

  }
}

