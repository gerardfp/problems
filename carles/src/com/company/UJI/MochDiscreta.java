package com.company.UJI;

import com.company.Util;

public class MochDiscreta {
  private final int[] v;
  private final int[] w;
  private final int W;

  public MochDiscreta(int[] v, int[] w, int W) {
    this.v = v;
    this.w = w;
    this.W = W;
  }

  public static void main(String[] args) {
    int[] v = {90, 75, 60, 20, 10};
    int[] w = {4, 3, 3, 2, 2};

    int W = 6;

    MochDiscreta moch = new MochDiscreta(v, w, W);
    System.out.println(moch.maxValue());
  }

  private double maxValue() {
    double val[][] = new double[v.length + 1][W + 1];
    int path[][][] = new int[v.length + 1][W+ 1][2];

    for (int i = 1; i <= v.length; i++) {
      for (int j = 1; j <= W; j++) {
        double utilitzaElement = Double.NEGATIVE_INFINITY;
        double noUtilitzaElement = val[i - 1][j];

        if (w[i-1] <= j) {
          utilitzaElement = v[i-1] + val[i - 1][j - w[i-1]];
        }

        if (utilitzaElement > noUtilitzaElement) {
          val[i][j] = utilitzaElement;

          path[i][j][0] = i - 1;
          path[i][j][1] = j - w[i-1];
        } else {
          val[i][j] = noUtilitzaElement;

          path[i][j][0] = i - 1;
          path[i][j][1] = j;
        }
      }
    }
    Util.printMatrix(path);
    int i = w.length;
    int j = W;


    while (j!=0){
      i=path[i][j][0];
      int j_aux=path[i][j][1];

      if(j_aux != j)
        System.out.printf("(%d, %d)\n", i, j);

      j=path[i][j][1];
    }
    return val[v.length][W];
  }
}
