package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Riu {

  private final float[][] posicions;
  private final ArrayDeque<Integer> visitats;
  final HashMap<Integer, Integer> visitatsHash;

  public Riu(float[][] posicions) {
    this.posicions = posicions;
    this.visitats = new ArrayDeque<>();
    this.visitatsHash = new HashMap<>();
  }


  public static void main(String[] args) {
    float[][] posicions = new float[200][200];

    for (int i = 0; i < posicions.length; i++) {
      for (int j = 0; j < posicions[i].length; j++) {
        posicions[i][j] = ((float) Math.random() * 200) + 1;
      }
    }

    Riu riu = new Riu(posicions);
    System.out.println(riu.minCost());
    System.out.println(riu.ruta());
  }

  private float minCost() {
    HashMap<Integer, Float> cache = new HashMap<>();
    return minCost(0, posicions.length - 1, cache);
  }

  private String ruta() {
    String out = "";

    int ini = 0;

    while (ini != posicions.length - 1 ) {
      out += ini;

      int aux = visitatsHash.get(ini);
      if(aux != posicions.length - 1) {
        out += " -> ";
      }
      ini = aux;
    }

    out += " -> " + (posicions.length - 1);

    return out;
  }

  private float minCost(int ini, int fi, HashMap<Integer, Float> cache) {
    if (ini == fi) {
      return 0;
    } else {
      Integer key = ini;
      if (!cache.containsKey(key)) {
        float min = Float.MAX_VALUE;

        for (int i = 0; i < posicions.length; i++) {

          if (!visitats.contains(i)) {
            this.visitats.push(i);

            float costAct = posicions[ini][i];
            float cost = costAct + minCost(i, fi, cache);

            this.visitats.pop();

            if (cost < min) {
              min = cost;
              visitatsHash.put(ini, i);
            }
          }
        }

        cache.put(key, min);
      }
      return cache.get(key);
    }
  }
}
