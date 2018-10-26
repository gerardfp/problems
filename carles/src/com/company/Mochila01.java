package com.company;

public class Mochila01 {
  private final int W;
  private final int[] weight;
  private final int[] value;

  public Mochila01(int[] value, int[] weight, int W) {
    this.value = value;
    this.weight = weight;
    this.W = W;
  }

  public static void main(String[] args) {
    int[] value = {0, 60, 100, 120};
    int[] weight = {0, 12, 20, 30};
    int W = 50;

    Mochila01 moc = new Mochila01(value, weight, W);
    System.out.println(moc.maxValue());
  }

  private int maxValue() {
    int[][] val = new int[W + 1][value.length + 1];

    for (int i = 1; i <= W; i++) {
      for (int j = 1; j < value.length; j++) {
        int agafo = Integer.MIN_VALUE;
        if (weight[j] <= i) {
          agafo = value[j] + val[i - weight[j]][j - 1];
        }

        int noAgafo = val[i][j - 1];

        int max = Math.max(agafo, noAgafo);

        val[i][j] = max;
      }
    }
    return val[W][value.length - 1];
  }
}
