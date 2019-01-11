package com.company.UJI;

import com.company.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Hort {
  private final int C;
  private HashMap<String, Integer> v;
  private HashMap<String, String> path;

  public Hort(int C, HashMap<String, Integer> v) {
    this.C = C;
    this.v = v;

    this.path = new HashMap<>();
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("AbonarCampos"));

    int C = sc.nextInt();
    int M = sc.nextInt();
    int N = sc.nextInt();

    //int[][][] v = new int[C][M + 1][N + 1];

    HashMap<String, Integer> v = new HashMap<>();
    for (int i = 0; i < C; i++) {
      for (int j = 0; j <= M; j++) {
        for (int k = 0; k <= N; k++) {
          v.put(String.format("%d,%d,%d", i,j,k), sc.nextInt());
        }
      }
    }

    System.out.println(v);

    Hort hort = new Hort(C, v);
    //System.out.println(hort.maxValue());
    System.out.println(hort.maxValueRecursiu(M, N));
    //System.out.println(maxValorRecursiu(C - 1, M, N, v));
    //System.out.println(maxValorRecursiuLog(C - 1, M, N, v, 1));

  }

  static int maxValorRecursiuLog(int C, int M, int N, int[][][] v, int l) { // l => nivell recursió per al Log
    if (C < 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    for (int m = 0; m <= M; m++) {
      for (int n = 0; n <= N; n++) {
        System.out.printf("%" + (l + 1) * 4 + "s%s\n", " ", "v(" + C + ", " + m + ", " + n + ")   CALL " + (C - 1) + " " + (M - m) + " " + (N - n) + "  ===> " + (v[C][m][n]) + maxValorRecursiu(C - 1, M - m, N - n, v));

        int valor = v[C][m][n] + maxValorRecursiuLog(C - 1, M - m, N - n, v, l + 1);

        if (valor > max) {
          max = valor;
        }
      }
    }
    return max;
  }


  static int maxValorRecursiu(int c, int M, int N, int[][][] v) {

    if (c < 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    for (int m = 0; m <= M; m++) {
      for (int n = 0; n <= N; n++) {
        int valor = v[c][m][n] + maxValorRecursiu(c - 1, M - m, N - n, v);
        if (valor > max) {
          max = valor;
        }
      }
    }
    return max;
  }


  private double maxValueRecursiu(int M, int N) {
    double result = _maxValueRecursiu(M, N);
    System.out.println(path);

    return result;
  }

  private double _maxValueRecursiu(int M, int N) {
    double max = Double.NEGATIVE_INFINITY;
    for (int c = 0; c < C; c++) {
      for (int m = 0; m <= M; m++) {
        for (int n = 0; n <= N; n++) {
          if (m != 0 || n != 0) {
            double aux = _maxValueRecursiu(M - m, N - n) + v.get(String.format("%d,%d,%d" ,c,m,n));
            if (aux > max){
              max = aux;
              path.put(String.format("%d,%d", M, N), String.format("%d,%d - c:%d - %f", m, n, c, aux));
            }
          }
        }
      }
    }

    return max;
  }

  /*private double maxValue() {
    double[][] val = new double[2 + 1][2 + 1];

    for (int i = 0; i <= 2; i++) {
      for (int j = 0; j <= 2; j++) {
        if (i == 0 && j == 0) {
          val[i][j] = 0;
        } else {
          double max = Double.NEGATIVE_INFINITY;
          for (int k = 0; k <= i; k++) {
            for (int l = 0; l <= j; l++) {
              for (int m = 0; m < 2; m++) {
                double aux = v[m][k][l] + val[i - k][j - l];
                if (aux > max)
                  max = aux;
              }
            }
          }
          val[i][j] = max;
        }
      }
    }

    Util.printMatrix(val);
    return val[2][2];
  }*/
}
