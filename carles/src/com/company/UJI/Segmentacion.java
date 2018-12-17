package com.company.UJI;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Segmentacion {
  private final String sentence;
  private final HashMap<String, Double> pr;

  public Segmentacion(String sentence, HashMap<String, Double> pr) {
    this.sentence = sentence;
    this.pr = pr;
  }

  public static void main(String[] args) {
    HashMap<String, Double> pr = new HashMap<>();

    pr.put("a", 0.05);
    pr.put("aca", 0.01);
    pr.put("acas", 0.01);
    pr.put("ad", 0.01);
    pr.put("asa", 0.02);
    pr.put("as", 0.03);
    pr.put("la", 0.12);
    pr.put("laca", 0.05);
    pr.put("lacas", 0.05);
    pr.put("ca", 0.01);
    pr.put("cadena", 0.06);
    pr.put("cas", 0.05);
    pr.put("casa", 0.08);
    pr.put("casaca", 0.05);
    pr.put("da", 0.05);
    pr.put("de", 0.05);
    pr.put("den", 0.02);
    pr.put("e", 0.02);
    pr.put("en", 0.11);
    pr.put("na", 0.01);
    pr.put("nada", 0.07);
    pr.put("sacad", 0.02);

    String sentence = "lacasacadenada";

    Segmentacion segment = new Segmentacion(sentence, pr);

    System.out.println(segment.mesProvable());
  }

  private double mesProvable() {
    double[] val = new double[sentence.length() + 1];

    val[0] = 1;

    for (int i = 1; i <= sentence.length(); i++) {
      double max = Double.NEGATIVE_INFINITY;
      for (String key : pr.keySet()) {
        if (i >= key.length() &&
                sentence.substring(i - key.length(), i).equals(key)) {
          double aux = pr.get(key) * val[i - key.length()];
          if(aux > max)
            max = aux;
        }
      }

      val[i] = max;
    }

    System.out.println(Arrays.toString(val));
    return val[sentence.length()];
  }
}