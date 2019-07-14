package com.company;

import java.util.ArrayList;

public class Levenshtein {

    static int levenshtein(String word1, String word2){
        int[][] D = new int[word1.length()+1][word2.length()+1];


        for (int i = 1; i <= word1.length(); i++) {
            D[i][0] = i;
        }

        for (int j = 1; j <= word2.length(); j++) {
            D[0][j] = j;
            for (int i = 1; i <= word1.length(); i++) {
                int INS = D[i-1][j]+1;
                int DEL = D[i][j-1]+1;
                int SUB = D[i-1][j-1] + (word1.charAt(i-1) != word2.charAt(j-1) ? 1 : 0);

                D[i][j] = Math.min(INS, Math.min(DEL, SUB));
            }
        }

        Util.printMatrix(D);
        ArrayList<String> edicion = new ArrayList<>();
        int i = word1.length(), j = word2.length();
        while(i > 0 || j > 0){
            if(D[i][j] == D[i-1][j] + 1){
                edicion.add(0,"insertar " + word1.charAt(i-1));
                i--;
            }
            else if(D[i][j] == D[i][j-1]+1){
                edicion.add(0, "borrar " + word2.charAt(j-1));
                j--;
            }
            else if(D[i][j] == D[i-1][j-1] + (word1.charAt(i-1) != word2.charAt(j-1) ? 1 : 0)){
                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    edicion.add(0, "sustituir " + word1.charAt(i - 1) + " por " + word2.charAt(j - 1));
                }
                i--;
                j--;
            }
        }

        System.out.println(edicion);
        return D[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
//        System.out.println(levenshtein("zoologicoarchaeologist", "zoogeologist"));
    }
}

/*
"zoologicoarchaeologist"
"zoogeologist"
 */