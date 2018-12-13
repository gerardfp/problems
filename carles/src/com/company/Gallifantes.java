package com.company;

public class Gallifantes {
  private final int m;
  private final int n;
  private final int[][] k;

  public Gallifantes(int m, int n, int[][] k) {
    this.m = m;
    this.n = n;
    this.k = k;
  }
  /*
  13 Abril 2005 - Ex1
   */

  public static void main(String[] args) {
    int M = 3;
    int N = 3;
    int k[][] = {
            {0, 1, 3, 5},
            {0, 1, 2, 7},
            {0, 2, 1, 5}
    };

    Gallifantes galli = new Gallifantes(M, N, k);
    System.out.println(galli.maxKodamas());
  }

  private double maxKodamas() {
    double[][] val = new double[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 && j == 0) {
          val[i][j] = 0;
        } else if (i == 0 || j == 0) {
          val[i][j] = Double.NEGATIVE_INFINITY;
        } else {
          double max = Double.NEGATIVE_INFINITY;
          for (int l = 1; l <= i; l++) {
            double value = k[j-1][l] + val[i-l][j-1];

            if (value > max){
              max = value;
            }
          }

          val[i][j] = max;
          Util.printMatrix(val);
        }
      }
    }

    return val[m][n];
  }
}
