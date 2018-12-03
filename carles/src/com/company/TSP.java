package com.company;

import java.util.ArrayDeque;

public class TSP {
  private ArrayDeque<Integer> ruta;
  private ArrayDeque<Integer> rutaMin;
  private int costMin;
  private int costAct;
  private int[][] graph;

  public TSP(int[][] graph) {
    this.graph = graph;
    this.ruta = new ArrayDeque<Integer>();
    this.rutaMin = new ArrayDeque<Integer>();
    this.costMin = Integer.MAX_VALUE;
    this.costAct = 0;
  }

  /*també kruskal i prim*/

  public static void main(String[] args) {
    int[][] graph = new int[4][4];

    graph[0][1] = 10;
    graph[1][0] = 10;

    graph[0][2] = 15;
    graph[2][0] = 15;

    graph[1][2] = 35;
    graph[2][1] = 35;

    graph[0][3] = 20;
    graph[3][0] = 20;

    graph[1][3] = 25;
    graph[3][1] = 25;

    graph[2][3] = 30;
    graph[3][2] = 30;

    TSP tsp = new TSP(graph);
    tsp.minRouteBnB();
  }

  private int minRouteBnB() {
    ruta.add(0);
    _minRouteBnB(0);

    System.out.println(costMin);
    System.out.println(rutaMin);
    return costMin;
  }

  private void _minRouteBnB(int ini) {
    if (ruta.size() == graph.length) {
      if (graph[ini][0] != 0) {
        costAct += graph[ini][0];

        if (costMin > costAct) {
          costMin = costAct;
          rutaMin = new ArrayDeque<>(ruta);
          rutaMin.add(0);
        }
        costAct -= graph[ini][0];

      }
    } else {
      for (int i = 0; i < graph.length; i++) {
        if(graph[ini][i] != 0 && !ruta.contains(i) && costAct + graph[ini][i] < costMin){
          costAct += graph[ini][i];
          ruta.add(i);
          _minRouteBnB(i);

          ruta.remove(i);
          costAct -= graph[ini][i];
        }
      }
    }
  }

  private int minRouteBnBIter() {
    ruta.add(0);
    _minRouteBnB(0);

    System.out.println(costMin);
    System.out.println(rutaMin);
    return costMin;
  }


}
