package com.company;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class GrafProbabilitat {
  private final double[][] posicions;
  private final float provMax;
  private final ArrayDeque<Integer> ruta;
  private final ArrayDeque<Object> rutaMax;

  public GrafProbabilitat(double[][] posicions) {
    this.posicions = posicions;
    this.provMax = 0;
    this.ruta = new ArrayDeque<>();
    this.rutaMax = new ArrayDeque<>();
  }

  private float maximitza() {
    HashMap<Integer, Float> cache = new HashMap<>();
    ruta.add(0);
    return maximitza(0, posicions.length-1, cache);
  }

  private float maximitza(int inici, int fin, HashMap<Integer, Float> cache) {
    if(inici == fin){
      return 1;
    } else {
      if(!cache.containsKey(inici)){
        float prov = 0;
        for (int i = 0; i < posicions[inici].length; i++) {
          float aux = (float) posicions[inici][i];

          if(aux != 0 && !ruta.contains(i) && aux >= prov){

            ruta.add(i);
            float value = aux * maximitza(i, fin, cache);

            if (value > prov){
              prov = value;

              rutaMax.clear();
              rutaMax.addAll(ruta);
            }

            ruta.remove(i);
          }
        }

        cache.put(inici, prov);
      }

      return cache.get(inici);
    }
  }

  public static void main(String[] args) {
    double[][] posicions = new double[20][20];

    for (int i = 0; i < posicions.length; i++) {
      for (int j = 0; j < posicions[i].length; j++) {
        posicions[i][j] = Math.random();
      }
    }

    GrafProbabilitat graf = new GrafProbabilitat(posicions);
    System.out.println(graf.maximitza());
    System.out.println(graf.ruta);
  }
}
