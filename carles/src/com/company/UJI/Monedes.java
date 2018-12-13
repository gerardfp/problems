package com.company.UJI;

import com.company.Util;

import java.util.Arrays;

public class Monedes {
  private final int[] m;
  private final int Q;
  private int[] n;

  public Monedes(int[] m, int Q, int[] n) {

    this.m = m;
    this.Q = Q;
    this.n = n;
  }

  public static void main(String[] args) {
    int[] m = {1, 2, 5, 10, 20, 50};
    int[] n = {3, 1, 4, 1, 2, 1};
    int Q = 24;

    Monedes mon = new Monedes(m, Q, n);
    mon.quantMin();
    mon.quantMinEnLimitacio();
  }

  private int quantMin() {
    double[] val = new double[Q + 1];
    int[] path = new int[Q + 1];

    for (int i = 1; i < Q + 1; i++) {
      Double min = Double.POSITIVE_INFINITY;
      for (int j = 0; j < m.length; j++) {
        if (i >= m[j]) {
          Double aux = 1 + val[i - m[j]];
          if (aux < min)
            min = aux;
          val[i] = min;
          path[i] = i - m[j];
        }
      }
    }

    System.out.println(Arrays.toString(val));
    System.out.println(Arrays.toString(path));
    return (int) val[Q];
  }

  private double quantMinEnLimitacio() {
    double val[][] = new double[m.length + 1][Q + 1];

    for (int j = 1; j <= Q; j++) {
      val[0][j] = Double.POSITIVE_INFINITY;
    }

    for (int i = 1; i <= m.length; i++) {
      for (int j = 1; j <= Q; j++) {
        double min = Double.POSITIVE_INFINITY;

        for (int k = 0; k <= n[i-1]; k++) {
          if(k*m[i-1] <= j){
            double aux = k + val[i-1][j-k*m[i-1]];

            if(aux < min)
              min = aux;
          }
        }

        val[i][j] = min;
      }
    }

    Util.printMatrix(val);
    return val[m.length][Q];
  }
}
