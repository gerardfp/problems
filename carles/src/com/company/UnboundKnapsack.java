package com.company;

import java.util.Arrays;
import java.util.HashMap;

public class UnboundKnapsack {
  private final int[] values;
  private final int[] weight;
  private final int capacitat;
  private final HashMap<Integer, Integer> seleccionats;

  public UnboundKnapsack(int[] values, int[] weight, int capacitat) {
    this.values = values;
    this.weight = weight;
    this.capacitat = capacitat;
    this.seleccionats = new HashMap<Integer, Integer>();
  }

  public static void main(String[] args) {
    int[] values = {10, 40, 50, 70};
    int[] weight = {1, 3, 4, 5};
    int capacitat = 8;

    UnboundKnapsack knap = new UnboundKnapsack(values, weight, capacitat);
    System.out.println(knap.maxValue());
  }

  private int maxValue() {
    // V_c = MAX(V[i] + val[c - P[i]])
    
    int [] val = new int[capacitat + 1];
    
    val[0] = 0;

    for (int i = 0; i <= capacitat; i++) {

      int max = 0;

      for (int j = 0; j < values.length; j++) {
        if(i >= weight[j]){
          int v_i = values[j];
          int val_c = val[i - weight[j]];

          int v_c = v_i + val_c;

          if (v_c > max){
            max = v_c;
            val[i] = max;
          }
        }
      }
    }
    System.out.println(Arrays.toString(val));
    return val[capacitat];
  }
}
