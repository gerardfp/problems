package com.company;

public class Torre {
  private final int n;
  private final int[] p;
  private final int[] s;
  private final int[][][] seleccionats;

  public Torre(int n, int[] p, int[] s) {
    this.n = n;
    this.p = p;
    this.s = s;
    this.seleccionats = new int[n+1][s.length+1][2];
  }

  public static void main(String[] args) {
    int n = 5;
    int[] p = {1, 2, 3, 4, 5};
    int [] s = {1, 3, 6};

    Torre torre = new Torre(n, p, s);
    System.out.println(torre.minValue());
    torre.seleccionats();
  }

  private void seleccionats() {
    int i = seleccionats[n][s.length][0];
    int j = seleccionats[n][s.length][1];

    while(i!=0 && j!=0){
      int i_aux = seleccionats[i][j][0];
      int j_aux = seleccionats[i][j][1];

      if(i_aux!=i){
        System.out.printf("Donem %d passes\n", s[j-1]);
      }

      i = i_aux;
      j = j_aux;
    }
  }

  private int minValue() {
    int [][] val = new int[n+1][s.length+1];

    for (int i = 0; i <= n; i++) {
      val[i][0] = Integer.MAX_VALUE;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= s.length; j++) {
        int entra = Integer.MAX_VALUE;

        int noEntra = val[i][j-1];

        if (i>=s[j-1]){
          entra = p[i-1] + s[j-1] + val[i-s[j-1]][j];
        }

        if(entra < noEntra){
          val[i][j] = entra;
          seleccionats[i][j][0] = i-s[j-1];
          seleccionats[i][j][1] = j;
        } else {
          val[i][j] = noEntra;
          seleccionats[i][j][0] = i;
          seleccionats[i][j][1] = j-1;
        }
      }
    }

    return val[n][s.length];
  }
}
