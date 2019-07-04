package com.company;

public class Utils {

    public static void printMatrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j] == Integer.MIN_VALUE){
                    System.out.printf("  -");
                }else if(m[i][j] == Integer.MAX_VALUE){
                    System.out.printf("  +");
                }else {
                    System.out.printf("%3d", m[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("----");
    }

    public static void printMatrix(double[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j] == Double.NEGATIVE_INFINITY){
                    System.out.printf("     -");
                }else if(m[i][j] == Double.POSITIVE_INFINITY){
                    System.out.printf("     +");
                }else {
                    System.out.printf("%6.2f", m[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("----");
    }

    public static void printMatrix(boolean[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf(m[i][j] ? "W " : "· ");
            }
            System.out.println();
        }
        System.out.println("----");
    }

    public static void printMatrix(char[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf(m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----");
    }


    public  static void printArray(int[] a){
        for (int i = 0; i < a.length; i++) {
            if(a[i] == Integer.MIN_VALUE){
                System.out.printf("  -");
            }else if(a[i] == Integer.MAX_VALUE){
                System.out.printf("  +");
            }else {
                System.out.printf("%" + (maxDigits(a)+1) + "d", a[i]);
            }
        }
        System.out.println();
    }

    public static void printArray(double[] a){
        for (int i = 0; i < a.length; i++) {
            if(a[i] == Double.NEGATIVE_INFINITY){
                System.out.print("     -");
            }else if(a[i] == Double.POSITIVE_INFINITY){
                System.out.print("     +");
            }else {
                System.out.printf("%6.2f", a[i]);
            }
        }
        System.out.println();
        System.out.println("----");
    }

    public static int maxDigits(int[] a){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
            min = Math.min(min, a[i]);
        }

        return Math.max(String.valueOf(max).length(), String.valueOf(min).length());
    }
}
