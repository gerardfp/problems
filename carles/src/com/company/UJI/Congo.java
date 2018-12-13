package com.company.UJI;

import java.util.Arrays;

public class Congo {
  private int[][] costos;
  private double[][] prov;

  public Congo(int[][] costos, double[][] prov) {
    this.costos = costos;
    this.prov = prov;
  }

  public static void main(String[] args) {
    int[][] costos = new int[7 + 1][2];
    double[][] prov = new double[7 + 1][7 +1];

    costos[1][0] = 4;
    costos[1][1] = 6;
    costos[2][0] = 3;
    costos[2][1] = 7;
    costos[3][0] = 1;
    costos[3][1] = 4;
    costos[4][0] = 2;
    costos[4][1] = 7;
    costos[5][0] = 4;
    costos[5][1] = 5;
    costos[6][0] = 9;
    costos[6][1] = 0;

    prov[1][2] = 0.6;
    prov[1][3] = 0.4;
    prov[2][3] = 0.7;
    prov[2][4] = 0.3;
    prov[3][4] = 0.7;
    prov[3][5] = 0.3;
    prov[4][5] = 0.6;
    prov[4][6] = 0.4;
    prov[5][6] = 0.6;
    prov[5][7] = 0.4;
    prov[6][7] = 1.0;

    Congo congo = new Congo(costos, prov);
    congo.minCost();
    congo.maxProv();
  }

  private int minCost() {
    double[] val = new double[costos.length];
    int[] path = new int[costos.length];

    val[2] = costos[1][0];

    for (int i = 3; i < costos.length; i++) {
      double unBot = costos[i-1][0] + val[i-1];
      double dosBots = costos[i-2][1] + val[i-2];

      if(unBot < dosBots){
        val[i] = unBot;
        path[i] = i-1;
      } else {
        val[i] = dosBots;
        path[i] = i-2;
      }
    }

    System.out.println(Arrays.toString(val));
    System.out.println(Arrays.toString(path));

    return (int) val[val.length-1];
  }

  private void maxProv() {
    double [] val = new double[costos.length];
    int[] path = new int[prov.length];

    val[2] = 0.6;

    for (int i = 3; i < val.length; i++) {
      double unBot = prov[i-1][i] * val[i-1];
      double dosBots = prov[i-2][i] * val[i-2];

      if (unBot > dosBots){
        val[i] = unBot;
        path[i] = i-1;
      } else {
        val[i] = dosBots;
        path[i] = i-2;
      }
    }

    System.out.println(Arrays.toString(val));
    System.out.println(Arrays.toString(path));

  }
}
