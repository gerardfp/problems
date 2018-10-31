package com.company;

import java.util.Arrays;

public class Videojoc {
  private final int[] p;
  private final int v;
  private final int[][][] seleccionats;

  public Videojoc(int[] p, int v) {
    this.p = p;
    this.v = v;
    this.seleccionats = new int[v + 1][p.length + 1][2];
  }

  public static void main(String[] args) {
    /*
     [Examen de junio 2015] En un videojuego el jugador puede recoger n tipos de objetos.
     Cada tipo de objeto le proporciona una determinada cantidad de puntos, de tipo entero.
     Sea p un vector de talla n con dichas cantidades. El objetivo del jugador es acumular
     exactamente una cantidad preestablecida v de puntos. La cantidad disponible de objetos
     de cada tipo es ilimitada. Existe un tipo de objeto que proporciona solamente un punto,
     lo que garantiza que el objetivo siempre se puede conseguir. Necesitamos un algoritmo que,
     dados p y v, calcule la mínima cantidad de objetos que necesita el jugador para sumar v
     puntos.

     Por ejemplo, si p = {1, 21, 5, 25} y v = 63 entonces la solución sería 3, correspondiente
     a coger 3 objetos de 21 puntos para sumar 63. Con el mismo p y v = 65 la solución sería 5,
     correspondiente a coger 2 objetos de 25 puntos y 3 de 5 puntos para sumar 65, o bien a
     coger 3 objetos de 21 puntos y 2 de 1 punto.

     Como ejemplo adicional para comprobar si tu solución es correcta, con p = {1, 4, 6, 10} y v = 22
     la solución es 3, correspondiente a 22 = 10 + 6 + 6 (lo cual es mejor que 22 = 10 + 10 + 1 + 1 y
     que 22 = 6 + 6 + 6 + 4).
    */

    int[] p = {1, 21, 5, 25} ;
    int v =  65;

    Videojoc joc = new Videojoc(p, v);
    System.out.println(joc.minObjects());
    joc.seleccionats();
  }

  private void seleccionats() {
    int i = seleccionats[v][p.length][0];
    int j = seleccionats[v][p.length][1];

    while (i != 0 && j != 0){
      int i_aux = seleccionats[i][j][0];
      int j_aux = seleccionats[i][j][1];

      if(i_aux!=i)
        System.out.println("Agafem el item " + j + " amb valor " + p[j-1]);

      i = i_aux;
      j = j_aux;
    }
  }

  private int minObjects() {
    int[][] val = new int[v + 1][p.length + 1];

    for (int i = 0; i <= v; i++) {
      val[i][0] = Integer.MAX_VALUE;
    }

    for (int i = 1; i <= v; i++) {
      for (int j = 1; j <= p.length; j++) {
        int agrega = Integer.MAX_VALUE;
        int noAgrega = val[i][j - 1];
        if (i >= p[j - 1]) {
          agrega = 1 + val[i - p[j - 1]][j];
        }

        if (agrega < noAgrega) {
          val[i][j] = agrega;
          seleccionats[i][j][0] = i - p[j - 1];
          seleccionats[i][j][1] = j;
        } else {
          val[i][j] = noAgrega;
          seleccionats[i][j][0] = i;
          seleccionats[i][j][1] = j - 1;
        }
      }
    }

    return val[v][p.length];
  }
}
