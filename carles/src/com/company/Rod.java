package com.company;

import java.util.HashMap;

public class Rod {
  private final int[] preus;
  HashMap<Integer, Integer> seleccionats;

  public Rod(int[] preus) {
    this.preus = preus;
    this.seleccionats = new HashMap<Integer, Integer>();
  }

  public static void main(String[] args) {
    int[] preus = {0, 1, 5, 8, 9, 10, 17, 17, 20};

    //V_c = MAX(V[Ci] + val[C - Ci]

    Rod rod = new Rod(preus);
    System.out.println(rod.optimitza());
    System.out.println(rod.seleccionats);

    System.out.println(rod.optimitzaIter());
    rod.seleccionatsIter();

  }

  private void seleccionatsIter() {
    int pes = preus.length - 1;

    while (pes > 0){
      System.out.println("Tallem " + seleccionats.get(pes) + " i guanyem " + preus[seleccionats.get(pes)]);
      pes -= seleccionats.get(pes);
    }

  }

  private int optimitzaIter() {
    int[] val = new int[preus.length];
    seleccionats = new HashMap<>();

    val [0] = 0;

    for (int i = 1; i < preus.length; i++) {
      int max = 0;

      for (int j = 0; j <= i; j++) {
        int value = preus[j] + val[i - j];

        if(value > max){
          max = value;
          seleccionats.put(i, j);
        }
      }

      val[i] = max;
    }

    return val[preus.length - 1];
  }


  private int optimitza() {
    HashMap<Integer, Integer> cache = new HashMap<>();
    return optimitza(preus.length, cache);
  }

  private int optimitza(int length, HashMap<Integer, Integer> cache) {
    if (length == 0) {
      return 0;
    } else {
      if (!cache.containsKey(length)) {
        int max = 0;
        for (int i = 1; i < length; i++) {
          int valueAct = preus [i];

          int valueResta = optimitza(length - i, cache);

          int value = valueAct + valueResta;

          if(value > max){
            max = value;
            seleccionats.put(length, i);

          }
        }
        cache.put(length, max);

      }
      return cache.get(length);
    }
  }

}
