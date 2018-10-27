package com.company;

import java.util.Arrays;
import java.util.HashMap;

public class Mochila01 {
  private final int W;
  private final int[] weight;
  private final int[] value;
  private final int[][][] seleccionats;

  public Mochila01(int[] value, int[] weight, int W) {
    this.value = value;
    this.weight = weight;
    this.W = W;
    this.seleccionats = new int[W + 1][value.length + 1][2];
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
          agafo = value[j - 1] + val[i - weight[j - 1]][j - 1];
        }

        int noAgafo = val[i][j - 1];

        int max = 0;
        String seleccionat;
        if (agafo > noAgafo) {
          max = agafo;
          seleccionats[i][j][0] = i - weight[j - 1];
          seleccionats[i][j][1] = j - 1;
        } else {
          max = noAgafo;
          seleccionats[i][j][0] = i;
          seleccionats[i][j][1] = j - 1;
        }

        val[i][j] = max;
      }
    }
    return val[W][value.length];
  }

  void seleccionats() {
    int i = W;
    int j = value.length;

    while (i != 0 && j != 0) {
      int aux_i = seleccionats[i][j][0];
      int aux_j = seleccionats[i][j][1];

      i = aux_i;
      j= aux_j;

      System.out.println("Agafem l'item " + j + " amb valor " + value[j]);
    }

  }
}
