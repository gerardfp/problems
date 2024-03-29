package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Se quiere abonar C campos con M metros cúbicos de agua y N sacos de abono. La utilización
 * de m metros de agua y n sacos de abono en el campo c permite obtener una cosecha cuyo valor
 * es v(c,m,n). ¿Cuantos sacos y metros hay que invertir en cada campo para obtener una cosecha
 * de valor máximo?
 */

public class AbonarCampos {

    static int maxValorDynamic(int C, int M, int N){
        int[][][] K = new int[C+1][M+1][N+1];

        for (int m = 0; m <=M; m++) {
            for (int n = 0; n <=N; n++) {
                int max = 0;
                for (int c = 0; c < C; c++) {
                    for (int mm = 0; mm <= m; mm++) {
                        for (int nn = 0; nn <= n; nn++) {
                            int val = v(c, m, n) + K[c][M - mm][N - nn];
                            if (val > max) {
                                max = val;
                            }
                        }
                    }
                }
                K[C][m][n] = max;
//                Util.printMatrix(K);
            }
        }

        return K[C][M][N];
    }

    static int maxValorRecursiu(int C, int M, int N){

        if(C < 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                int valor = v(C, m, n) + maxValorRecursiu(C - 1, M - m, N - n);
                if (valor > max) {
                    max = valor;
                }
            }
        }
        return max;
    }

    static int maxValorRecursiuLog(int C, int M, int N, int l){ // l => nivell recursió per al Log
        if(C < 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                System.out.printf("%"+(l+1)*4+"s%s\n"," ", "v(" + C + ", " + m + ", "+ n+")   CALL " + (C-1) + " " + (M-m) + " " + (N-n));

                int valor = v(C, m, n) + maxValorRecursiuLog(C - 1, M - m, N - n, l + 1);

                if (valor > max) {
                    max = valor;
                }
            }
        }
        return max;
    }

    static int[][][] v;
    static int v(int c, int m, int n){
        return v[c][m][n];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("AbonarCampos"));

        int C = sc.nextInt();
        int M = sc.nextInt();
        int N = sc.nextInt();

        while(C!=0) {
            v = new int[C][M + 1][N + 1];

            for (int i = 0; i < C; i++) {
                for (int j = 0; j <= M; j++) {
                    for (int k = 0; k <= N; k++) {
                        v[i][j][k] = sc.nextInt();
                    }
                }
            }

            System.out.println("Dynamic  " + maxValorDynamic(C, M, N));
            System.out.println("Recursiu " + maxValorRecursiu(C-1, M, N));
            System.out.println(maxValorRecursiuLog(C-1, M, N,0));

            C = sc.nextInt();
            M = sc.nextInt();
            N = sc.nextInt();
        }

        System.out.println(maxValorDynamic(C, M, N));
        System.out.println(maxValorRecursiu(C-1, M, N));
        System.out.println(maxValorRecursiuLog(C-1, M, N,0));
    }
}

