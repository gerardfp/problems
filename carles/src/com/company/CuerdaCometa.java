package com.company;// TODO: no va (Time Limit Exceeded)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CuerdaCometa {

  //static Scanner sc = new Scanner(System.in);
  static Scanner sc;

  static {
    try {
      sc = new Scanner(new File("p31"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    StringBuilder stringBuilder = new StringBuilder();

    while (sc.hasNextInt()) {
      int N = sc.nextInt();
      int L = sc.nextInt();

      int[] l = new int[N];
      int[] c = new int[N];
      int minLength = Integer.MAX_VALUE;
      int sumLength = 0;

      for (int i = 0; i < N; i++) {
        l[i] = sc.nextInt();
        if (l[i] < minLength) minLength = l[i];
        sumLength += l[i];
        c[i] = sc.nextInt();
      }

      if (sumLength < L) {
        stringBuilder.append("NO");
        stringBuilder.append('\n');
      } else {
        // Simplificar els datos de entrada
        int gcd = gcd(l, L);
        if (gcd > 1) {
          L /= gcd;
          minLength /= gcd;
          for (int i = 0; i < N; i++) {
            l[i] /= gcd;
          }
        }


        int[][] Kt = new int[N + 1][L + 1]; // total maneras
        int[][] Kn = new int[N + 1][L + 1]; // minim cuerdas
        int[][] Kc = new int[N + 1][L + 1]; // minim coste

        int maxLength = 0;
        for (int j = 1; j < N + 1; j++) {

          maxLength += l[j - 1];

          if (l[j - 1] < L + 1) {
            Kt[j][l[j - 1]] = 1;
            Kn[j][l[j - 1]] = 1;
            Kc[j][l[j - 1]] = c[j - 1];
          }

          for (int i = minLength; i < L + 1 && i <= maxLength; i++) {

            Kt[j][i] += Kt[j - 1][i];
            if (i - l[j - 1] >= 0 && l[j - 1] < L + 1) {
              Kt[j][i] += Kt[j - 1][i - l[j - 1]];
            }

            if (i - l[j - 1] >= 0) {
              if (Kn[j - 1][i - l[j - 1]] > 0) {
                if (Kn[j - 1][i] > 0) {
                  Kc[j][i] = Math.min(Kc[j - 1][i], Kc[j - 1][i - l[j - 1]] + 1);
                } else {
                  Kn[j][i] = Kn[j - 1][i - l[j - 1]] + 1;
                }
              }
              if (Kn[j][i] == 0) {
                Kn[j][i] = Kn[j - 1][i];
              }
            } else {
              Kn[j][i] = Kn[j - 1][i];
            }

            if (i - l[j - 1] >= 0) {
              if (Kc[j - 1][i - l[j - 1]] > 0) {
                if (Kc[j - 1][i] > 0) {
                  Kc[j][i] = Math.min(Kc[j - 1][i], Kc[j - 1][i - l[j - 1]] + c[j - 1]);
                } else {
                  Kc[j][i] = Kc[j - 1][i - l[j - 1]] + c[j - 1];
                }
              }
              if (Kc[j][i] == 0) {
                Kc[j][i] = Kc[j - 1][i];
              }
            } else {
              Kc[j][i] = Kc[j - 1][i];
            }
          }
        }

        if (Kt[N][L] > 0) {
          stringBuilder.append("SI ");
          stringBuilder.append(Kt[N][L]);
          stringBuilder.append(" ");
          stringBuilder.append(Kn[N][L]);
          stringBuilder.append(" ");
          stringBuilder.append(Kc[N][L]);
          stringBuilder.append('\n');
        } else {
          stringBuilder.append("NO");
          stringBuilder.append('\n');
        }
      }
    }
    System.out.print(stringBuilder);
  }

  private static int gcd(int a, int b) {
    while (b > 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  private static int gcd(int[] input, int L) {
    int result = L;
    for (int i = 0; i < input.length; i++) result = gcd(result, input[i]);
    return result;
  }
}
