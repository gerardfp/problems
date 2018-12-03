package com.company.UJI;

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
