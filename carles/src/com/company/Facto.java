package com.company;

import java.math.BigInteger;
import java.util.HashMap;

public class Facto {

  private HashMap<Integer, BigInteger> cache;
  private int n;

  private BigInteger calcula() {
    if (!cache.containsKey(n)) {
      BigInteger aux;

      if (n < 1) {
        aux = BigInteger.ONE;
      } else {
        n--;
        BigInteger facto_1 = calcula();
        n++;
        aux = BigInteger.valueOf(n).multiply(facto_1);
      }

      cache.put(n, aux);
    }

    return cache.get(n);
  }

  public Facto(int n) {
    this.cache = new HashMap<>();
    this.n = n;
  }

  public static void main(String[] args) {
    Facto facto = new Facto(10);
    System.out.println(facto.calcula());
  }
}
