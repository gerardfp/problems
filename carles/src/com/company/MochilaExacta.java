package com.company;

import java.util.ArrayDeque;

public class MochilaExacta {
  private final int[] values;
  private final int[] weights;
  private final int w;
  private int[][][] sel;

  public MochilaExacta(int[] values, int[] weights, int w) {

    this.values = values;
    this.weights = weights;
    this.w = w;
  }

  public static void main(String[] args) {
    int[] values = {6, 2, 1, 12};
    int[] weights = {1, 2, 10, 3};
    int W = 5;

    MochilaExacta moch = new MochilaExacta(values, weights, W);
    System.out.println(moch.maxValue());
    System.out.println(moch.path());

  }

  private ArrayDeque<Integer> path() {
    ArrayDeque<Integer> path = new ArrayDeque<>();

    int i = w;
    int j = values.length;

    while (i != 0 && j != 0) {
      int aux_i = sel[i][j][0];
      int aux_j = sel[i][j][1];

      path.push(j);

      i = aux_i;
      j= aux_j;
    }

    return path;
  }

  private double maxValue() {
    double[][] val = new double[w + 1][values.length + 1];
    sel = new int[w + 1][values.length + 1][2];

    for (int i = 0; i <= w; i++) {
      for (int j = 0; j <= values.length; j++) {
        if (i == 0 && j == 0) {
          val[i][j] = 0;
        } else if (i == 0) {
          val[i][j] = 0;
        } else if (j == 0) {
          val[i][j] = Double.NEGATIVE_INFINITY;
        } else {
          double noPosem = val[i][j - 1];
          double posem = Double.NEGATIVE_INFINITY;
          if (i >= weights[j - 1]) {
            posem = values[j - 1] + val[i - weights[j - 1]][j - 1];
          }

          if (noPosem > posem) {
            val[i][j] = noPosem;
            sel[i][j][0] = i;
            sel[i][j][1] = j - 1;
          } else {
            val[i][j] = posem;
            sel[i][j][0] = i - weights[j - 1];
            sel[i][j][1] = j - 1;
          }
        }
      }
    }

    return val[w][values.length];
  }
}
