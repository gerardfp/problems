package com.company;

public class AsignacionRecursos {

  private final int R;
  private final int[][] v;
  private final int[] li;
  private final int[] lm;

  private AsignacionRecursos(int[][] v, int[] li, int[] lm, int R) {
    this.v = v;
    this.li = li;
    this.lm = lm;
    this.R = R;
  }

  private double maxBeneficio() {
    double[][] val = new double[R + 1][v.length + 1];

    for (int i = 0; i <= R; i++) {
      for (int j = 0; j <= v.length; j++) {

        if (i == 0 && j == 0) {
          val[i][j] = 0;
        } else if (i == 0 || j == 0) {
          val[i][j] = Double.NEGATIVE_INFINITY;
        } else {

          int k_min = li[j - 1];
          int k_max = lm[j - 1];

          double max = Double.NEGATIVE_INFINITY;

          for (int k = k_min; k <= k_max; k++) {
            if (i >= k) {
              double value = v[j - 1][k] + val[i - k][j - 1];

              if (value > max) {
                max = value;
              }
            }
          }
          val[i][j] = max;
        }

        Util.printMatrix(val);
      }
    }

    return val[R][v.length];
  }

  public static void main(String[] args) {

    int[] lm = {3, 4, 4};
    int[] li = {2, 1, 2};

    int[][] v = {
            {0, 2, 3, 4, 4, 4, 4},
            {0, 1, 3, 5, 5, 5, 5},
            {0, 1, 2, 2, 2, 2, 2}
    };

    AsignacionRecursos recursos = new AsignacionRecursos(v, li, lm, 6);

    System.out.println(recursos.maxBeneficio());
  }
}
