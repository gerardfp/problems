package com.company.UJI;

import com.company.Util;

public class CamiKEixos {
  private final int[][] graph;
  private final int k;
  private final int ini;
  private final int desti;

  public CamiKEixos(int[][] graph, int k, int ini, int desti) {
    this.graph = graph;
    this.k = k;
    this.ini = ini;
    this.desti = desti;
  }

  public static void main(String[] args) {
    int[][] graph = new int[6][6];
    graph[0][1] = 3;
    graph[0][3] = 1;

    graph[1][0] = 1;
    graph[1][1] = 2;
    graph[1][2] = 2;
    graph[1][4] = -2;
    graph[1][5] = 3;

    graph[2][5] = 1;

    graph[3][1] = 1;

    graph[4][3] = 2;
    graph[4][5] = 2;

    int ini = 0;
    int desti = 5;

    for (int k = 0; k < 6; k++) {
      CamiKEixos cami = new CamiKEixos(graph, k, ini, desti);
      System.out.printf("De 0 a 5 en %d aristes: %d\n", k,  cami.mesCurt());
      System.out.printf("De 0 a 5 en %d aristes: %d\n", k,  cami.mesCurtIter());
    }

  }

  private int mesCurtIter(){
    double [][] val = new double [desti+ 1][k + 1];

    for (int i = 0; i <= desti; i++) {
      for (int j = 0; j <= k; j++) {
        if(j==0 && i==desti){
          val[i][j] = 0;
        } else if(j==0){
          val[i][j] = Double.POSITIVE_INFINITY;
        } else {
          double min = Double.POSITIVE_INFINITY;
          for (int l = 0; l < graph.length; l++) {
            if(graph[i][l]!=0){
             double aux = graph[i][l] + val[l][j-1];
            }
          }
        }
      }
    }

    Util.printMatrix(val);

    return (int) val[desti][k];
  }

  private int mesCurt() {
    return (int) _mesCurt(ini, desti, k);
  }

  private double _mesCurt(int ini, int desti, int k) {
    if (k == 0) {
      if (ini == desti) {
        return 0;
      } else {
        return Double.POSITIVE_INFINITY;
      }
    } else {
      double min = Double.POSITIVE_INFINITY;

      for (int i = 0; i < graph.length; i++) {
        if (graph[ini][i] != 0) {
          double aux = graph[ini][i] + _mesCurt(i, desti, k - 1);

          if (aux < min)
            min = aux;
        }
      }

      return min;
    }
  }
}
