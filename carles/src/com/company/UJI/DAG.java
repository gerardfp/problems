package com.company.UJI;

import com.company.Util;

import java.util.Arrays;

public class DAG {
  private int[][] graph;

  public DAG(int[][] graph) {
    this.graph = graph;
  }

  public static void main(String[] args) {
    int[][] graph = new int[6][6];

    graph[0][1] = 3;
    graph[0][3] = 1;

    graph[1][2] = 2;
    graph[1][4] = 2;
    graph[1][5] = 2;

    graph[2][4] = 3;
    graph[2][5] = 1;

    graph[3][1] = 1;

    graph[4][5] = 2;

    DAG dag = new DAG(graph);
    System.out.println(dag.minRouteBellman());
    System.out.println(dag.minRouteBellmanK());
  }

  private double minRouteBellmanK() {
    double[][] val = new double[graph.length][graph.length - 1];

    for (int k = 0; k < graph.length - 1; k++) {
      for (int i = 0; i < graph.length; i++) {
        if (i == 0 && k == 0) {
          val[i][k] = 0;
        } else if (i == 0 || k == 0) {
          val[i][k] = Double.POSITIVE_INFINITY;
        } else {
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
    }

    double min = Double.POSITIVE_INFINITY;
    for (int i = 0; i < val[val.length-1].length; i++) {
      double aux = val[5][i];
      if(aux < min)
        min = aux;
    }

    return min;
  }

  private int minRouteBellman() {
    double val[] = new double[graph.length];
    int path[] = new int[graph.length];

    for (int i = 1; i < val.length; i++) {
      val[i] = Double.POSITIVE_INFINITY;
    }

    for (int iter = 0; iter < graph.length - 1; iter++) {
      for (int node = 0; node < graph.length; node++) {
        for (int eix = 0; eix < graph.length; eix++) {
          if (graph[node][eix] != 0 && graph[node][eix] + val[node] < val[eix]) {
            val[eix] = graph[node][eix] + val[node];
            path[eix] = node;
          }
        }
      }
    }

    System.out.println(Arrays.toString(path));
    return (int) val[graph.length - 1];
  }


}
