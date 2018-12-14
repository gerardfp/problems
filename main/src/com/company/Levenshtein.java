package com.company;

import java.util.ArrayList;

public class Levenshtein {

    static int levenshtein(String m, String n){
        int[][] D = new int[m.length()+1][n.length()+1];
        ArrayList<String> edicion = new ArrayList<>();

        for (int i = 1; i <= m.length(); i++) {
            D[i][0] = D[i-1][0] + 1;
        }

        for (int j = 1; j <= n.length(); j++) {
            D[0][j] = D[0][j-1]+1;
            for (int i = 1; i <= m.length(); i++) {
                int INS = D[i-1][j]+1;
                int DEL = D[i][j-1]+1;
                int SUB = D[i-1][j-1] + (m.charAt(i-1) != n.charAt(j-1) ? 1 : 0);

                D[i][j] = Math.min(INS, Math.min(DEL, SUB));
            }
        }

        int i = m.length(), j = n.length();
        while(i > 0 || j > 0){
            if(D[i][j] == D[i-1][j] + 1){
                edicion.add(0,"insertar " + m.charAt(i-1));
                i--;
            }
            else if(D[i][j] == D[i][j-1]+1){
                edicion.add(0, "borrar " + n.charAt(j-1));
                j--;
            }
            else if(D[i][j] == D[i-1][j-1] + (m.charAt(i-1) != n.charAt(j-1) ? 1 : 0)){
                if (m.charAt(i-1) != n.charAt(j-1)) {
                    edicion.add(0, "sustituir " + m.charAt(i - 1) + " por " + n.charAt(j - 1));
                }
                i--;
                j--;
            }
        }

        System.out.println(edicion);
        return D[m.length()][n.length()];
    }

    public static void main(String[] args) {
        System.out.println(levenshtein("campos", "ejemplo"));
    }
}

/*

 */