package com.company;


public class Change {
  public static void main(String[] args) {
    int[] tipus = new int[]{1, 2, 3};
    int[] tipus2 = new int[]{1, 2, 3};

    Change change = new Change();
    System.out.println(change.minChange(tipus, 4));
    System.out.println(change.numChanges(tipus2, 4));
  }

  private int minChange(int[] tipus, int n) {

    double val[][] = new double[n + 1][tipus.length + 1];

    for (int i = 0; i <= n; i++) {
      val[i][0] = Double.POSITIVE_INFINITY;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= tipus.length; j++) {
        double entra = Double.POSITIVE_INFINITY;
        double noEntra = val[i][j - 1];

        if (i >= tipus[j - 1]) {
          entra = 1 + val[i - tipus[j - 1]][j];
        }

        val[i][j] = Math.min(entra, noEntra);
      }
    }

    return (int) val[n][tipus.length];
  }

  private int numChanges(int[] tipus, int n) {
    double val[][] = new double[n + 1][tipus.length + 1];

    //V[i,j] = MAX(V[i][j-1], 1 + V[i-t[j-1], j])

    for (int i = 0; i <= n; i++) {
      val[i][0] = Double.NEGATIVE_INFINITY;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= tipus.length; j++) {
        double noAgafem = val[i][j - 1];
        double agafem = Double.NEGATIVE_INFINITY;
        if (i >= tipus[j - 1]) {
          agafem = 1 + val[i - tipus[j - 1]][j];
        }

        val[i][j] = Math.max(noAgafem, agafem);

        Util.printMatrix(val);
      }
    }

    return (int) val[n][tipus.length];
  }
}
