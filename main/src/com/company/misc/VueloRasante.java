package com.company.misc;

import java.util.Scanner;

public class VueloRasante {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ncasos = scanner.nextInt();

        for (int c = 0; c < ncasos; c++) {
            int size = scanner.nextInt();

            int[] a = new int[size];

            for (int i = 0; i < size; i++) {
                a[i] = scanner.nextInt()+1;
                for (int j = 0; j < a.length; j++) {
                    int v = a[j];
                    while( ! llegaATodos(a, j, v)){
                        v++;
                    }
                    a[j] = v;
                }
            }
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();
        }
    }

    static boolean llegaATodos(int[] a, int i, int v){
        for (int j = 0; j < a.length; j++) {
            if (a[j]>v && Math.abs(i-j) < a[j]-v){
                return false;
            }
        }
        return true;
    }
}
