package com.company;

import com.sun.jdi.IncompatibleThreadStateException;

public class Change {
  public static void main(String[] args) {
    int[] tipus = new int[]{2, 5, 3, 6};
    int[] tipus2 = new int[]{1, 2, 3, 4, 5};

    Change change = new Change();
    System.out.println(change.minChange(tipus, 4));
    System.out.println(change.numChanges(tipus, 4));
  }

  private int minChange(int[] tipus, int n) {

    Double val[][] = new Double[n + 1][tipus.length + 1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= tipus.length; j++) {
        if (j == 0)
          val[i][0] = Double.POSITIVE_INFINITY;
        else
          val[i][j] = (double) 0;
      }
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= tipus.length; j++) {
        Double entra = Double.POSITIVE_INFINITY;
        Double noEntra = val[i][j - 1];

        if (i >= tipus[j - 1]) {
          entra = 1 + val[i - tipus[j - 1]][j];
        }

        val[i][j] = Math.min(entra, noEntra);
      }
    }

    return val[n][tipus.length].intValue();
  }

  private int numChanges(int[] tipus, int n) {
    Double val[][] = new Double[n + 1][tipus.length + 1];

    //V[i,j] = MAX(V[i][j-1], 1 + V[i-t[j-1], j])

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= tipus.length; j++) {
        if (j == 0)
          val[i][0] = Double.NEGATIVE_INFINITY;
        else
          val[i][j] = (double) 0;
      }
    }


    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= tipus.length; j++) {
        Double noAgafem = val[i][j - 1];
        Double agafem = Double.NEGATIVE_INFINITY;
        if (i >= tipus[j - 1]) {
          agafem = 1 + val[i - tipus[j - 1]][j];
        }

        val[i][j] = Math.max(noAgafem, agafem);

        //Util.printMatrix(val);
      }
    }

    return val[n][tipus.length].intValue();
  }
}
