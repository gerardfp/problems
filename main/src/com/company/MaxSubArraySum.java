package com.company;

/*
Producció = {5, 3, 7, 4, 2}
Pedidos = { 3, 6, 3, 2, 4};

Stock = { 2, -1, 3, 5, 3};

Tenim l'historic de producció, i de pedidos.

 */
public class MaxSubArraySum {

    public static void main(String[] args) {
        int[] arr = {-2, -3, -1, -4, -6};

        int [] sol = max(arr);

        System.out.println(sol[0] + " " + sol[1]);
    }

    static int[] max(int[] arr){
        int m = arr[0];
        int t = 0;

        int f = arr[0];
        int a = f;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > m){
                m = arr[i];
            }
            if(arr[i] >= 0){
                t += arr[i];
            }

            if(arr[i] + f > arr[i]){
                f += arr[i];
            } else {
                f = arr[i];
            }

            if(f > a) a = f;
        }

        return new int[]{m>=0?t:m, a};
    }
}
