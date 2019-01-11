package com.company.UJI;

import com.company.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KAristas {
  private int[][] graph;
  private int etapes;
  private int dest;

  public KAristas(int[][] graph, int etapes, int dest) {

    this.graph = graph;
    this.etapes = etapes;
    this.dest = dest;
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

    int dest = 5;

    for (int i = 0; i < 6; i++) {
      KAristas aristas = new KAristas(graph, i, dest);
      System.out.printf("Mínima distancia de 0 a 5 con %d aristas: RECUR: %d - ITER: %d\n",
              i,
              aristas.minValue(),
              aristas.minValueIter());
    }
  }

  private int minValueIter() {
    double[][] val = new double[graph.length][etapes + 1];

    for (int i = 1; i <= etapes; i++) {
      val[0][i] = Double.POSITIVE_INFINITY;
    }

    for (int i = 1; i < graph.length; i++) {
      val[i][0] = Double.POSITIVE_INFINITY;
    }

    for (int k = 1; k <= etapes; k++) {
      for (int i = 1; i < graph.length; i++) {
        double min = Double.POSITIVE_INFINITY;

        for (int j = 0; j < graph.length; j++) {
          if (graph[j][i] != 0) {
            Double aux = graph[j][i] + val[j][k - 1];

            if (aux < min)
              min = aux;
          }
        }

        val[i][k] = min;
      }
    }

    Util.printMatrix(val);
    return (int) val[dest][etapes];
  }


  private int minValue() {
    return (int) _minValue(etapes, 0);
  }

  private double _minValue(int k, int ini) {
    if (k == 0 && ini == dest)
      return 0;
    else if (k == 0 && ini != dest)
      return Double.POSITIVE_INFINITY;
    else {
      double min = Double.POSITIVE_INFINITY;

      for (int i = 0; i < graph.length; i++) {
        if (graph[ini][i] != 0) {
          double aux = _minValue(k - 1, i) + graph[ini][i];

          if (aux < min)
            min = aux;
        }
      }

      return min;
    }
  }
}
