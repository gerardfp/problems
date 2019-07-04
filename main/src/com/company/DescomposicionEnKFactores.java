package com.company;

public class DescomposicionEnKFactores {

    public static void main(String[] args) {
        desc(12,4);
    }

    /* passe, no esta be */

    static void desc(int n, int k){
        int[][] K = new int[k+1][n+1];

        for (int i = 0; i < n+1; i++) {
            K[0][i] = i;
        }

        for (int i = 0; i < k+1; i++) {
            K[i][1] = 1;
        }

        for (int i = 1; i < k+1; i++) {
            for (int j = 2; j < n+1; j++) {
                int min = Integer.MAX_VALUE;
                for (int l = 1; l < j; l++) {
                    if(j%l == 0){
                        if(j/l + K[i-1][l] < min){
                            min = j/l + K[i-1][l];
                        }
                    }
                }
                K[i][j] = min;
            }
        }

        Utils.printMatrix(K);
        System.out.println(K[k][n]);

    }
}
