package com.company;

public class Util {
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if(matrix[i][j] <= Integer.MIN_VALUE + 10){
          System.out.printf("  -");
        }else if(matrix[i][j] == Integer.MAX_VALUE){
          System.out.printf("  +");
        } else {
          System.out.printf("%3d", matrix[i][j]);
        }
      }
      System.out.println();
    }
    System.out.println("---");
  }

  public static void printArray(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(" " + matrix[j][i]);
      }
      System.out.println();
    }
    System.out.println("---");
  }

  static void printMatrix(double[][] m){
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if(m[i][j] == Double.NEGATIVE_INFINITY){
          System.out.printf("  -");
        }else if(m[i][j] == Double.POSITIVE_INFINITY){
          System.out.printf("  +");
        }else {
          System.out.printf("%3.0f", m[i][j]);
        }
      }
      System.out.println();
    }
    System.out.println("----");
  }


}
