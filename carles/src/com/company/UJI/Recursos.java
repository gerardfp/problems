package com.company.UJI;

import java.util.Arrays;

public class Recursos {
  private final int tipus;
  private final int quantitat;
  private final int[][] d;

  public Recursos(int tipus, int quantitat, int[][] d) {

    this.tipus = tipus;
    this.quantitat = quantitat;
    this.d = d;
  }

  public static void main(String[] args) {
    int tipus = 3;
    int quantitat = 6;

    int d[][] = {
            {0, 3, 5, 7, 7, 7, 7},
            {0, 4, 6, 7, 7, 7, 7},
            {0, 1, 2, 5, 5, 5, 5}
    };

    Recursos rec = new Recursos(tipus, quantitat, d);
    rec.maxBenefici();
  }

  private double maxBenefici() {
    double val [] = new double[quantitat+1];

    for (int i = 1; i <= quantitat; i++) {
      double max = Double.NEGATIVE_INFINITY;
      for (int j = 0; j < tipus; j++) {
        for (int k = 1; k <= i; k++) {
          double aux = d[j][k] + val[i-k];

          if(aux>max)
            max = aux;
        }
      }

      val[i] = max;
    }

    System.out.println(Arrays.toString(val));
    return val[quantitat];
  }
}
