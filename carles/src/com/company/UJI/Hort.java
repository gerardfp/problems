package com.company.UJI;

import com.company.Util;

public class Hort {
  private int[][][] v;

  public Hort(int[][][] v) {

    this.v = v;
  }

  public static void main(String[] args) {
    int[][][] v = new int[2][3][3];

    v[0][1][1] = 3;
    v[0][1][2] = 3;
    v[0][2][2] = 5;

    v[1][1][1] = 4;
    v[1][1][2] = 5;
    v[1][2][2] = 2;


    Hort hort = new Hort(v);
    System.out.println(hort.maxValue());
  }

  private double maxValue() {
    double[][] val = new double[2 + 1][2 + 1];

    for (int i = 0; i <= 2; i++) {
      for (int j = 0; j <= 2; j++) {
        if (i == 0 && j == 0) {
          val[i][j] = 0;
        } else {
          double max = Double.NEGATIVE_INFINITY;
          for (int k = 0; k <= i; k++) {
            for (int l = 0; l <= j; l++) {
              for (int m = 0; m < 2; m++) {
                double aux = v[m][k][l] + val[i - k][j - l];
                if (aux > max)
                  max = aux;
              }
            }
          }
          val[i][j] = max;
        }
      }
    }

    Util.printMatrix(val);
    return val[2][2];
  }
}
