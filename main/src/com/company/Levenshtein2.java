package com.company;

public class Levenshtein2 {

    public static void main(String[] args) {
        String a = "zoologiclogist";
        String b =  "zoogeologist";

        System.out.println(min(a,b));
        System.out.println(rec(a.length()-1,b.length()-1,a,b));

    }

    static int min(String a, String b){
        int[][] DP = new int[a.length()+1][b.length()+1];

        for (int i = 0; i < b.length()+1; i++) {
            DP[0][i] = i;
        }
        for (int i = 0; i < a.length()+1; i++) {
            DP[i][0] = i;
        }

        for (int i = 1; i < a.length()+1; i++) {
            for (int j = 1; j < b.length()+1; j++) {
                int ADD = DP[i-1][j]+1;
                int DEL = DP[i][j-1]+1;
                int SUB = DP[i-1][j-1] + (a.charAt(i-1) != b.charAt(j-1) ? 1 : 0);

                DP[i][j] = Math.min(ADD, Math.min(DEL, SUB));
            }
        }

        Util.printMatrix(DP);
        return DP[a.length()][b.length()];
    }

    static int rec(int i, int j, String a, String b){
        if(i==0 && j==0) return 0;
        if(i>0 && j==0) return rec(i-1, 0,a,b)+1;
        if(i==0 && j>0) return rec(0, j-1,a,b)+1;

        return Math.min(rec(i-1,j,a,b)+1, Math.min(rec(i,j-1,a,b)+1, rec(i-1,j-1,a,b) + (a.charAt(i) != b.charAt(j) ? 1 : 0)));
    }
}
