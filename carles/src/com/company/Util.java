package com.company;

public class Util {
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(" " + matrix[i][j]);
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

  public static void printMatrix(Double[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.printf("%d ", matrix[j][i].intValue());
      }
      System.out.println();
    }
    System.out.println("---");
  }


}
