package com.company;

import java.util.HashMap;

public class Mochila01 {
  private final int W;
  private final int[] weight;
  private final int[] value;
  private final HashMap<String, String> seleccionats;

  public Mochila01(int[] value, int[] weight, int W) {
    this.value = value;
    this.weight = weight;
    this.W = W;
    this.seleccionats = new HashMap<String, String>();
  }

  public static void main(String[] args) {
    int[] value = {0, 60, 100, 120};
    int[] weight = {0, 12, 20, 30};
    int W = 50;

    Mochila01 moc = new Mochila01(value, weight, W);
    System.out.println(moc.maxValue());
    moc.seleccionats();
  }

  private int maxValue() {
    int[][] val = new int[W + 1][value.length + 1];

    for (int i = 1; i <= W; i++) {
      for (int j = 1; j <= value.length; j++) {

        int agafo = Integer.MIN_VALUE;
        if (weight[j - 1] <= i) {
          agafo = value[j - 1] + val[i - weight[j-1]][j - 1];
        }

        int noAgafo = val[i][j - 1];

        int max = 0;
        String seleccionat;
        if (agafo > noAgafo) {
          max = agafo;
          seleccionat = String.format("(%d,%d)", i - weight[j-1], j - 1);
        } else {
          max = noAgafo;
          seleccionat = String.format("(%d,%d)", i, j - 1);
        }

        val[i][j] = max;
        String key = String.format("(%d,%d)", i, j);
        seleccionats.put(key, seleccionat);
      }
    }
    return val[W][value.length];
  }

  void seleccionats() {
    String key = String.format("(%d,%d)", W, value.length);
    while (seleccionats.get(key) != null) {
      String select = seleccionats.get(key);
      String[] split = select
              .replace("(", "")
              .replace(")", "")
              .split(",");

      int i = Integer.parseInt(split[0]);
      int j = Integer.parseInt(split[1]);

      System.out.println("Agafem el item " + j + ", de valor " + value[j]);

      key = select;
    }
  }
}
