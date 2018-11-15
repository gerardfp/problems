package com.company;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class Futbol {

  // http://dis.um.es/~ginesgm/files/doc/aed/ejerc4-1.pdf - 4.57

  private final double[][] posicions;
  private final float provMax;
  private final ArrayDeque<Integer> ruta;
  private final ArrayDeque<Object> rutaMax;
  private int[][][] seleccionats;


  public Futbol(double[][] posicions) {
    this.posicions = posicions;
    this.provMax = 0;
    this.ruta = new ArrayDeque<>();
    this.rutaMax = new ArrayDeque<>();
    this.seleccionats = new int[posicions.length + 1][posicions.length + 1][2];
  }

  public static void main(String[] args) {
    /*double[][] posicions = new double[5][5];

    for (int i = 0; i < posicions.length; i++) {
      for (int j = 0; j < posicions[i].length; j++) {
        posicions[i][j] = Math.random();
      }
    }*/

    double[][] distancies = new double[12][12];
    distancies[0][1] = 0.9;
    distancies[0][2] = 0.7;
    distancies[1][4] = 0.1;
    distancies[1][2] = 0.6;
    distancies[2][3] = 0.5;
    distancies[2][5] = 0.1;
    distancies[2][11] = 0.2;
    distancies[3][0] = 0.9;
    distancies[3][2] = 1;
    distancies[3][5] = 0.8;
    distancies[4][11] = 0.7;
    distancies[5][4] = 0.3;
    distancies[5][11] = 0.6;

    Futbol graf = new Futbol(distancies);
    System.out.println(graf.maximitza());
    System.out.println(graf.ruta);

    System.out.println(graf.maximitzaIter());
    graf.seleccionats();

    double[][] P = new double[5][5];
    P[0][3] = 1;
    P[1][4] = 1;
    P[2][1] = 1;
    P[3][2] = 1;


    double[][] negatius = new double[4][5];
    negatius[0][1] = 2;
    negatius[0][3] = 1;
    negatius[0][2] = 99;
    negatius[1][3] = 2;
    negatius[2][1] = -300;


    graf.wtf(P);
    System.out.println();
    graf.bellmanFord(P);
    System.out.println();
    graf.dijkstra(P);

    graf.wtf(negatius);
    System.out.println();
    graf.bellmanFord(negatius);
    System.out.println();
    graf.dijkstra(negatius);

    //System.out.println(graf.bellmanFordProb(distancies));
  }

  private double maximitzaIter() {
    double[][] val = new double[posicions.length + 1][posicions.length + 1];

    for (int i = 0; i < val[1].length; i++) {
      val[1][i] = 1;
    }

    for (int i = 2; i <= posicions.length; i++) {
      for (int j = 1; j <= posicions.length; j++) {
        double entra = 0;
        double noEntra = val[i][j - 1];

        if (posicions[j - 1][i - 1] != 0) {
          entra = posicions[j - 1][i - 1] * val[j][j - 1];
        }

        if (entra > noEntra) {
          val[i][j] = entra;
          seleccionats[i][j][0] = j;
          seleccionats[i][j][1] = j - 1;
        } else {
          seleccionats[i][j][0] = i;
          seleccionats[i][j][1] = j - 1;

          val[i][j] = noEntra;
        }

        //Util.printMatrix(val);
      }

    }

    return val[posicions.length][posicions.length];
  }

  private double wtf(double[][] p) {
    double[] val = new double[p.length];
    int[] path = new int[p.length];

    ArrayDeque<Integer> pendents = new ArrayDeque<>();
    ArrayDeque<Integer> visitats = new ArrayDeque<>();

    val[0] = 1;
    for (int j = 1; j < p.length; j++) {
      val[j] = Double.NEGATIVE_INFINITY;
    }

    pendents.push(0);
    while (!pendents.isEmpty()) {
      int act = pendents.getFirst();
      pendents.remove(act);

      System.out.printf(pendents.toString());

      //visitats.add(act);

      for (int i = 1; i < p[act].length; i++) {
        if (p[act][i] != 0) {
          if (!pendents.contains(i)) {
            pendents.addFirst(i);
          }

          if (val[i] < p[act][i] * val[act]) {
            val[i] = p[act][i] * val[act];
            path[i] = act;
          }
        }
      }
      System.out.println(Arrays.toString(val));
    }

    int i = p.length - 1;
    while (i != 0) {
      System.out.println("Al " + i + " li passa el " + path[i]);
      i = path[i];
    }

    return val[p.length - 1];
  }

  private double wtfProb(double[][] p) {
    double[] val = new double[p.length + 1];
    int[] path = new int[p.length + 1];

    ArrayDeque<Integer> pendents = new ArrayDeque<>();
    ArrayDeque<Integer> visitats = new ArrayDeque<>();

    pendents.push(1);

    val[1] = 1;

    for (int j = 2; j <= p.length; j++) {
      val[j] = Double.NEGATIVE_INFINITY;
    }

    while (!pendents.isEmpty()) {
      int act = pendents.getFirst();
      pendents.remove();

      visitats.add(act);

      for (int i = 2; i <= p[act - 1].length; i++) {
        double noAgafa = val[i];
        double agafa = Double.NEGATIVE_INFINITY;

        if (p[act - 1][i - 1] != 0) {
          agafa = p[act - 1][i - 1] * val[act];
          if (!visitats.contains(i)) {
            pendents.addLast(i);
          }
        }
        if (noAgafa > agafa) {
          val[i] = noAgafa;
        } else {
          val[i] = agafa;
          path[i] = act;
        }
      }

    }

    int i = p.length;

    while (i != 1) {
      int i_aux = path[i];
      System.out.println("Al " + i + " li passa el " + i_aux);
      i = i_aux;
    }

    return val[p.length];
  }

  private double bellmanFord(double[][] p) {
    double[] val = new double[p.length];

    for (int i = 1; i < p.length; i++) {
      val[i] = Double.POSITIVE_INFINITY;
    }

    for (int iter = 0; iter < p.length - 1; iter++) {
      for (int i = 0; i < p.length; i++) {
        for (int j = 0; j < p.length; j++) {
          if (p[i][j] != 0) {
            double value = p[i][j] + val[i];
            if (val[j] > value) {
              val[j] = value;
            }
          }
        }
      }

      System.out.println(Arrays.toString(val));
    }

    return val[p.length - 1];
  }

  private double bellmanFordProb(double[][] p) {
    double[] val = new double[p.length];

    val[0] = 1;

    for (int i = 1; i < p.length; i++) {
      val[i] = Double.NEGATIVE_INFINITY;
    }

    for (int iter = 0; iter < p.length - 1; iter++) {
      for (int i = 0; i < p.length; i++) {
        for (int j = 0; j < p.length; j++) {
          if (p[i][j] != 0) {
            double value = p[i][j] * val[i];
            if (val[j] < value) {
              val[j] = value;
            }
          }
        }
      }

      System.out.println(Arrays.toString(val));
    }

    return val[p.length - 1];
  }

  private double dijkstra(double[][] p) {
    double val[] = new double[p.length];

    for (int i = 1; i < p.length; i++) {
      val[i] = Double.POSITIVE_INFINITY;
    }

    ArrayDeque<Integer> pen = new ArrayDeque<>();
    //ArrayDeque<Integer> col = new ArrayDeque<>();

    for (int i = 0; i < p.length; i++) {
      pen.add(i);
    }

    while (!pen.isEmpty()) {
      //busquem
      double value = Double.POSITIVE_INFINITY;
      int act = -1;

      for (int i = 0; i < p.length; i++) {
        if (pen.contains(i) && val[i] <= value) {
          value = val[i];
          act = i;
        }
      }

      pen.remove(act);

      for (int i = 0; i < p.length; i++) {
        if (p[act][i] != 0 && val[i] > p[act][i] + val[act]) {
          val[i] = p[act][i] + val[act];
        }
      }
      System.out.println(Arrays.toString(val));
    }

    return val[val.length - 1];
  }

  void seleccionats() {
    int i = seleccionats[posicions.length][posicions.length][0];
    int j = seleccionats[posicions.length][posicions.length][1];

    while (i != 0 && j != 0) {
      int i_aux = seleccionats[i][j][0];
      int j_aux = seleccionats[i][j][1];

      if (i_aux != i) {
        System.out.printf("Al jugador %d li ha passat el %d\n", i, j);
      }

      i = i_aux;
      j = j_aux;
    }
  }

  private float maximitza() {
    HashMap<Integer, Float> cache = new HashMap<>();
    ruta.add(0);
    return maximitza(0, posicions.length - 1, cache);
  }

  private float maximitza(int inici, int fin, HashMap<Integer, Float> cache) {
    if (inici == fin) {
      return 1;
    } else {
      if (!cache.containsKey(inici)) {
        float prov = 0;
        for (int i = 0; i < posicions[inici].length; i++) {
          float aux = (float) posicions[inici][i];

          if (aux != 0 && !ruta.contains(i) && aux >= prov) {

            ruta.add(i);
            float value = aux * maximitza(i, fin, cache);

            if (value > prov) {
              prov = value;

              rutaMax.clear();
              rutaMax.addAll(ruta);
            }

            ruta.remove(i);
          }
        }

        cache.put(inici, prov);
      }

      return cache.get(inici);
    }
  }
}
