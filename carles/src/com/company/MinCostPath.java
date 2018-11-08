package com.company;

public class MinCostPath {
  public static void main(String[] args) {
    MinCostPath path = new MinCostPath();

    int[][] costs = {
            {1, 2, 3},
            {4, 8, 2},
            {1, 5, 3}
    };

    System.out.println(path.minCost(costs));
  }

  private int minCost(int[][] costs) {
    int[][] val = new int[costs.length][costs[0].length];

    for (int j = 0; j < costs.length; j++) {
      for (int i = 0; i < costs.length; i++) {
        int min = Integer.MAX_VALUE;

        if (i == 0 && j == 0) {
          min = 0;
        }
        if ((j > 0) && (i > 0)) {
          int diagonal = val[i - 1][j - 1];
          if (diagonal < min) {
            min = diagonal;
          }
        }

        if (i > 0) {
          int esquerra = val[i - 1][j];
          if (esquerra < min) {
            min = esquerra;
          }
        }

        if (j > 0) {
          int dalt = val[i][j - 1];
          if (dalt < min) {
            min = dalt;
          }
        }

        val[i][j] = min + costs[i][j];

        Util.printMatrix(val);
      }
    }

    return val[costs.length-1][costs[0].length-1];
  }

}
