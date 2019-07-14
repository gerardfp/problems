package com.company;

import java.util.Arrays;

public class FlowerPlanting {

    static int N = 3;
    static int[][] paths = {{1,2},{2,3},{3,1}};
    static int[] sol = new int[N];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(flowerPlanting(N,paths)));
    }

    static int[] flowerPlanting(int N, int[][] paths){
        int[] sol = new int[N];
        for (int n = 0; n < N; n++) {
            for (int f = 1; f <= N; f++) {
                boolean isValid=true;
                for (int i = 0; i < paths.length; i++) {
                    if(paths[i][0]-1 == n && sol[paths[i][1]-1] == f){
                        isValid = false;
                        break;

                    }
                    if(paths[i][1]-1 == n && sol[paths[i][0]-1] == f){
                        isValid = false;
                        break;
                    }
                }
                if(isValid){
                    sol[n] = f;
                    break;
                }
            }
        }

        return sol;
    }

}
