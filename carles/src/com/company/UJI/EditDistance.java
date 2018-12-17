package com.company.UJI;

import com.company.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class EditDistance {
  private final String word1;
  private final String word2;

  public EditDistance(String word1, String word2) {

    this.word1 = word1;
    this.word2 = word2;
  }

  public static void main(String[] args) {

    String word1 = "casa";
    String word2 = "saca";


    EditDistance edit = new EditDistance(word1, word2);
    System.out.println(edit.minDistance());
  }

  private int minDistance() {
    double[][] val = new double[word1.length() + 1][word2.length() + 1];

    for (int i = 0; i <= word1.length(); i++) {
      for (int j = 0; j <= word2.length(); j++) {
        if (i == 0 && j == 0) {
          val[i][j] = 0;
        } else if (i == 0 || j == 0) {
          val[i][j] = Math.max(i, j);
        } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          val[i][j] = val[i - 1][j - 1];
        } else {
          double min = Double.POSITIVE_INFINITY;

          if (1 + val[i - 1][j - 1] < min) {
            min = 1 + val[i - 1][j - 1];
          }

          if (1 + val[i][j - 1] < min) {
            min = 1 + val[i][j - 1];
          }

          if (1 + val[i - 1][j] < min) {
            min = 1 + val[i - 1][j];
          }

          val[i][j] = min;
        }
      }
    }

    /*ArrayList<String> path = new ArrayList<>();

    int i = word1.length();
    int j = word2.length();

    while (i != 0 || j != 0) {
      if (val[i][j] == val[i - 1][j - 1]) {
        i--;
        j--;
      } else if (val[i][j] == val[i - 1][j]) {

      }
    }*/

    return (int) val[word1.length()][word2.length()];
  }
}
