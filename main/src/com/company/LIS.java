package com.company;

import java.util.Scanner;

public class LIS {

    static int[] seq = {0,1,2,0,3};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] m = new int[n];

        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        System.out.println(lis(m));
    }

    public static int lis(int[] arr){
        int[] k = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            k[i] = 1;
        }

        int m = -1;
        for (int i = 1; i < arr.length; i++) {
            int max = -1;
            for (int j = 0; j <i ; j++) {
                if(arr[i] > arr[j] && k[i] < k[j]+1){
                    k[i] = k[j]+1;
                    if(k[j]+1 > m) m = k[j]+1;
                }
            }
        }

        return m;
    }
}
