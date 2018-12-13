package com.company;

import static com.sun.tools.doclint.Entity.sup;

public class Maig2004_2 {
  private final int[][] d;
  private final int[] sup;
  private final int[] inf;
  private final int r;

  public Maig2004_2(int[][] d, int[] sup, int[] inf, int r) {
    this.d = d;
    this.sup = sup;
    this.inf = inf;
    this.r = r;
  }

  public static void main(String[] args) {
    int d[][] = {
            {0, 3, 5, 7, 7, 7, 7},
            {0, 4, 6, 7, 7, 7, 7},
            {0, 1, 2, 5, 5, 5, 5}
    };

    int s[] = {5, 2, 6};
    int i[] = {2, 2, 2};

    int r = 6;
    Maig2004_2 recursos = new Maig2004_2(d, s, i, r);
    recursos.maxBenefici();
  }

  private void maxBenefici() {
    int[][] val = new int[r + 1][d.length + 1];

    for (int i = 0; i <= r; i++) {

      int max = Integer.MIN_VALUE;
      for (int j = 0; j <= d.length; j++) {

        if (i == 0 && j == 0) {
          val[i][j] = 0;
        } else if (i == 0 || j == 0) {
          val[i][j] = Integer.MIN_VALUE;
        } else {
          int minFiquem = inf[j - 1];
          int maxFiquem = Math.min(i, sup[j - 1]);

          for (int k = minFiquem; k <= maxFiquem; k++) {
            int valor;

            valor = d[j - 1][k] + val[i - k][j - 1];

            if (valor > max) {
              max = valor;
            }
          }

          val[i][j] = max;

          Util.printMatrix(val);
        }
      }
    }

  }
}
