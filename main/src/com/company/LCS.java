package com.company;

import java.util.Arrays;

public class LCS {

    static char[] lcsDynamicPath(String M, String N){
        char[] a = M.toCharArray();
        char[] b = N.toCharArray();
        int[][] K = new int[a.length+1][b.length+1];

        for (int i = 1; i <=a.length ; i++) {
            for (int j = 1; j <= b.length ; j++) {
                if(a[i-1] == b[j-1]){
                    K[i][j] = K[i-1][j-1]+1;
                }else{
                    K[i][j] = Math.max(K[i][j-1], K[i-1][j]);
                }
            }
        }

        // path
        int i=a.length;
        int j=b.length;
        int n = K[a.length][b.length];
        char[] lcs = new char[n];

        while(i != 0 && j != 0){
            if(a[i-1] == b[j-1]){
                lcs[--n] = a[i-1];
                j--;
                i--;
            }else{
                if(K[i-1][j] > K[i][j-1]){
                    i--;
                }else{
                    j--;
                }
            }
        }
        return lcs;
    }

    static int lcsDynamic(String M, String N){
        int[][] K = new int[M.length()+1][N.length()+1];

        for (int i = 1; i <= M.length(); i++) {
            for (int j = 1; j <= N.length(); j++) {
                if(M.charAt(i-1) != N.charAt(j-1)){
                    K[i][j] = Math.max(K[i-1][j], K[i][j-1]);
                } else {
                    K[i][j] = K[i-1][j-1] + 1;
                }
            }
        }
        return K[M.length()][N.length()];
    }

    static int lcsRecursiu(String M, String N, int m, int n){
        if(m == 0 || n == 0){
            return 0;
        }

        if(M.charAt(m-1) != N.charAt(n-1)){
            return Math.max(lcsRecursiu(M, N, m-1, n), lcsRecursiu(M, N, m, n-1));
        } else {
            return lcsRecursiu(M, N, m-1, n-1) + 1;
        }
    }

    static int lcsRecursiuLog(String M, String N, int m, int n, int level){
        System.out.printf("%"+level*6+"s%s\n", " ", "CALL " + m + " " + n + " ***************");
        if(m == 0 || n == 0){
            System.out.printf("%"+level*6+"s%s\n", " ", "CA00 " + m + " " + n + " ====> CERO");
            return 0;
        }

        if(M.charAt(m-1) != N.charAt(n-1)){
            System.out.printf("%"+level*6+"s%s\n", " ", "CA!= " + (m-1) + " " + n + "    CALL " + (m) + " " + (n-1) +" ******** ");
            int max = Math.max(lcsRecursiuLog(M, N, m-1, n, level+1), lcsRecursiuLog(M, N, m, n-1, level+1));

            System.out.printf("%"+level*6+"s%s\n", " ", "CA!= " + (m-1) + " " + n + "    CALL " + (m) + " " + (n-1) +" ====> " + max);
            return max;
        } else {
            System.out.printf("%"+level*6+"s%s\n", " ", "CA== " + (m-1) + " " + (n-1) + " ********** ");
            int val = lcsRecursiuLog(M, N, m-1, n-1, level+1) + 1;

            System.out.printf("%"+level*6+"s%s\n", " ", "CA== " + (m-1) + " " + (n-1) + " ====> " + val);
            return val;
        }
    }

    public static void main(String[] args) {
        String M = "abchhd";
        String N = "azbcvd";
        System.out.println(lcsDynamic(M, N));
        System.out.println(lcsRecursiu(M, N, M.length(), N.length()));
        System.out.println(Arrays.toString(lcsDynamicPath(M,N)));
//        System.out.println(lcsRecursiuLog(M, N, M.length(), N.length(), 1));
    }
}