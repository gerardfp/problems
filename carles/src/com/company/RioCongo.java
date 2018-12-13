package com.company;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RioCongo {
  private final int len;
  private final HashMap<String, Integer> preus;

  public RioCongo(int len, HashMap<String, Integer> preus) {
    this.len = len;
    this.preus = preus;
  }

  public static void main(String[] args) {
    HashMap<String, Integer> preus = new HashMap<>();
    preus.put("1 - 2", 4);
    preus.put("1 - 3", 6);
    preus.put("2 - 3", 3);
    preus.put("2 - 4", 7);
    preus.put("3 - 4", 1);
    preus.put("3 - 5", 4);
    preus.put("4 - 5", 2);
    preus.put("4 - 6", 7);
    preus.put("5 - 6", 4);
    preus.put("5 - 7", 5);
    preus.put("6 - 7", 9);

    RioCongo congo = new RioCongo(7, preus);
    System.out.println(congo.minValue());
  }

  private double minValue() {
    double[] val = new double[len + 1];
    int[] ant = new int[len + 1];

    val[2] = preus.get(String.format("%d - %d", 1, 2));

    for (int i = 3; i <= len; i++) {

      double uno = preus.get(String.format("%d - %d", i - 1, i)) + val[i - 1];
      double dos = preus.get(String.format("%d - %d", i - 2, i)) + val[i - 2];

      if (uno > dos) {
        val[i] = uno;
        ant[i] = i - 1;
      } else {
        val[i] = dos;
        ant[i] = i - 2;
      }

      val[i] = Math.min(uno, dos);
    }

    System.out.println(Arrays.toString(val));
    System.out.println(Arrays.toString(ant));

    ArrayDeque<Integer> path = new ArrayDeque<>();

    int i = ant.length - 1;
    while (i != 0) {
      if (ant[i] != 0)
        path.push(ant[i]);

      i = ant[i];
    }

    System.out.println(path);

    return val[len];
  }
}
