package com.company;
/*
    Una importante empresa de inversiones ha dise˜nado una prueba para los aspirantes a trabajar en ella:
     les asigna una cantidad de dinero y, durante T meses, deben hacer un total de M inversiones de dicha
      cantidad con el objetivo de obtener el maximo beneficio. Para ello, pueden invertir el dinero a
      plazo fijo en N diferentes productos financieros. Cada producto i (1 ≤ i ≤ N) tiene asociado el
      plazo de recuperacion d(i) y el beneficio b(i). El procedimiento de inversion consiste en
      seleccionar un producto i, invertir el benefici completo en el y, transcurrido el tiempo d(i),
      recoger el beneficio y repetir el proceso. No hay inconveniente en invertir en un mismo producto
      mas de una vez. Durante todo el periodo T, el benefici debe estar invertido en algun producto y,
                               una vez finalizado
    el periodo, hay que haber recuperado el benefici inicial.
    Deseas saber en que productos realizaras las inversiones para obtener el maximo beneficio posible.
    */

public class Inversiones {

  private final int[] recuperacio;
  private final int[] benefici;

  public Inversiones(int[] recuperacio, int[] benefici) {
    this.recuperacio = recuperacio;
    this.benefici = benefici;
  }

  public static void main(String[] args) {
    int[] recuperacio = {1, 3, 5, 2};
    int[] benefici = {10, 33, 57, 19};
    Inversiones inver = new Inversiones(recuperacio, benefici);
    inver.max(6, 4);
  }

  private void max(int mesos, int quantitat) {
    double[][] val = new double[quantitat + 1][mesos + 1];
    int[][][] path = new int[quantitat + 1][mesos + 1][3];

    for (int i = 0; i <= quantitat; i++) {
      for (int j = 0; j <= mesos; j++) {
        if (i != 0 && j == 0) {
          val[i][j] = Double.NEGATIVE_INFINITY;
        } else if(i ==0 && j !=0){
          val[i][j] = Double.NEGATIVE_INFINITY;
        } else if (i > 0 && j > 0) {
          double max = Double.NEGATIVE_INFINITY;
          for (int k = 0; k < recuperacio.length; k++) {
            if (recuperacio[k] <= j) {
              double aux = benefici[k] + val[i - 1][j - recuperacio[k]];
              if (aux > max) {
                max = aux;
                path[i][j][0] = i - 1;
                path[i][j][1] = j - recuperacio[k];
                path[i][j][2] = (k+1)*1000;
              }
            }
          }

          val[i][j] = max;
        }
      }
    }

    Util.printMatrix(val);
    reconstruirPath(path);
  }

  private void reconstruirPath(int[][][] path) {
    int i = path.length - 1;
    int j = path[0].length - 1;

    while (j != 0) {
      int iAux = i;
      int jAux = j;

      i = path[iAux][jAux][0];
      j = path[iAux][jAux][1];

      if (j != jAux) {
        System.out.println("Producte: "  + path[iAux][jAux][2]);
      }
    }
  }
}
