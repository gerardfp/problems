package com.company.UJI;

public class LCS {
  private final String word1;
  private final String word2;

  public LCS(String word1, String word2) {

    this.word1 = word1;
    this.word2 = word2;
  }

  public static void main(String[] args) {
    String word1 = "comparsa";
    String word2 = "causarte";

    LCS lcs = new LCS(word1, word2);
    System.out.println(lcs.lcs());
  }

  private int lcs() {
    double[][] val = new double[word1.length() + 1][word2.length() + 1];

    for (int i = 0; i <= word1.length(); i++) {
      for (int j = 0; j <= word2.length(); j++) {
        if(i==0 & j==0){
          val[i][j] = 0;
        } else if(i==0 || j==0){
          val[i][j] = Double.NEGATIVE_INFINITY;
        } else if(word1.charAt(i-1) == word2.charAt(j-1)){
          val[i][j] = 1 + val[i-1][j-1];
        } else {
          val[i][j] = Math.max(val[i-1][j], val[i][j-1]);
        }
      }
    }

    return (int) val[word1.length()][word2.length()];
  }
}
