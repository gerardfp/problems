package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cometa {
  private final int[] l;
  private final int[] p;
  private final int L;

  /*static Scanner sc;

  static {
    try {
      sc = new Scanner(new File("p31"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }*/

  static Scanner sc = new Scanner(System.in);


  public Cometa(int[] l, int[] p, int L) {
    this.l = l;
    this.p = p;
    this.L = L;
  }

  public static void main(String[] args) {

    while (sc.hasNextInt()) {
      int N = sc.nextInt();
      int L = sc.nextInt();

      int[] l = new int[N];
      int[] p = new int[N];

      for (int i = 0; i < N; i++) {
        l[i] = sc.nextInt();
        p[i] = sc.nextInt();
      }

      Cometa cometa = new Cometa(l, p, L);
      cometa.optimitza();
    }

  }

  private void optimitza() {
    double[][] val1 = new double[L + 1][p.length + 1];
    double[][] val2 = new double[L + 1][p.length + 1];
    double[][] val3 = new double[p.length + 1][L + 1];

    double formes = 0, minCordes, minPreu = 0, formesGerard = 0;

    for (int i = 0; i < val1.length; i++) {
      for (int j = 0; j < val1[i].length; j++) {
        if (i == 0 && j == 0) {
          val1[i][j] = 0;
        } else if (j == 0) {
          val1[i][j] = Double.POSITIVE_INFINITY;
        } else {
          double entra = Double.POSITIVE_INFINITY;
          double noEntra = val1[i][j - 1];

          if (i >= l[j - 1]) {
            entra = 1 + val1[i - l[j - 1]][j - 1];
          }

          double min = Math.min(entra, noEntra);
          val1[i][j] = min;
        }
      }
    }
    minCordes = val1[L][p.length];

    if (minCordes != Double.POSITIVE_INFINITY) {
      for (int i = 0; i < val2.length; i++) {
        for (int j = 0; j < val2[i].length; j++) {
          if (i == 0 && j == 0) {
            val2[i][j] = 0;
          } else if (j == 0) {
            val2[i][j] = Double.POSITIVE_INFINITY;
          } else {
            double entra = Double.POSITIVE_INFINITY;
            double noEntra = val2[i][j - 1];

            if (i >= l[j - 1]) {
              entra = p[j - 1] + val2[i - l[j - 1]][j - 1];
            }

            double min = Math.min(entra, noEntra);
            val2[i][j] = min;
          }
        }
      }

      minPreu = val2[L][p.length];

      for (int i = 0; i < val3.length; i++) {
        val3[i][0] = 1;
      }

      for (int i = 1; i <= p.length; i++) {
        for (int j = 1; j <= L; j++) {
          double entra = 0;
          double noEntra = val3[i-1][j];
          if(j >= l[i-1])
            entra = val3[i - 1][j - l[i-1]];

          val3[i][j] = entra + noEntra;
        }
      }

      formes = val3[p.length][L];
    }


    if (minCordes == Double.POSITIVE_INFINITY) {
      System.out.println("NO");
    } else {
      System.out.println("SI " + ((int) formes) + " " + ((int) minCordes) + " " + ((int) minPreu));
    }

  }

  private int contaFormes(int i, int tamany) {
    if (tamany == 0) {
      return 1;
    } else {
      int agafa = 0, count = 0;
      if (i < l.length) {

        if (l[i] <= L) {
          agafa = contaFormes(i + 1, tamany - l[i]);
        }
        count = agafa + contaFormes(i + 1, tamany);
      }
      return count;
    }
  }
}
