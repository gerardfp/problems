package com.company;

public class Lladre {
  private final int[] v;

  public Lladre(int[] v) {
    this.v = v;
  }

  public static void main(String[] args) {
    int [] v = {6, 1, 2, 7};
    Lladre lladre = new Lladre(v);
    System.out.println(lladre.maxValue());
  }

  private int maxValue() {
    int[][] val = new int[v.length+1][v.length+1];

    for (int i = 1; i <=v.length ; i++) {
      for (int j = 1; j <= v.length; j++) {
        int noEntra = val[i][j-1];
        int entra = Integer.MIN_VALUE;

        if(j==1){
          entra = v[0];
        } else if(j==2){
            entra = Math.max(v[0], v[1]);
        } else {
          if(i>=j)
            entra = v[j-1] + val[i-2][j];
        }

        val[i][j] = Math.max(noEntra, entra);
      }
    }

    return val[v.length][v.length];
  }
}
