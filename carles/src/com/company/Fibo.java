package com.company;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.function.Function;

public class Fibo {
  static BigInteger fibo(int n, HashMap<Integer, BigInteger> cache) {

    if (!cache.containsKey(n)) {
      BigInteger aux;
      if (n <= 1) {
        aux = BigInteger.valueOf(n);
      } else {
        BigInteger fibo1 = fibo(n - 1, cache);
        BigInteger fibo2 = fibo(n - 2, cache);

        aux = fibo1.add(fibo2);
      }
      cache.put(n, aux);
    }

    return cache.get(n);
  }

  static BigInteger fibo(int n) {
    HashMap<Integer, BigInteger> cache = new HashMap<>();

    return fibo(n, cache);
  }

  static BigInteger fiboIter(int n) {
    BigInteger[] cache = new BigInteger[n+1];

    cache[0] = BigInteger.ZERO;
    cache[1] = BigInteger.ONE;

    for (int i = 2; i <= n; i++) {
      BigInteger fibo_1 = cache[i-1];
      BigInteger fibo_2 = cache[i-2];

      cache[i] = fibo_1.add(fibo_2);
    }

    return cache[n];
  }

  private static BigInteger fiboMemo(int n) {
    HashMap<Integer, BigInteger> cache = new HashMap<>();
    return fiboMemo(n, cache);
  }

  private static BigInteger fiboMemo(int n, HashMap<Integer, BigInteger> cache) {
    if (n <= 1) {
      return BigInteger.valueOf(n);
    } else {
      if (!cache.containsKey(n)) {
        BigInteger fibo_1 = fiboMemo(n - 1, cache);
        BigInteger fibo_2 = fiboMemo(n - 2, cache);

        BigInteger result = fibo_1.add(fibo_2);

        cache.put(n, result);
      }

      return cache.get(n);
    }
  }

  private static void time(Function<Integer, BigInteger> func, int i) {
    //start
    long lStartTime = System.currentTimeMillis();

    System.out.println(func.apply(i));
    //end
    long lEndTime = System.currentTimeMillis();

    //time elapsed
    long output = lEndTime - lStartTime;
    System.out.println("Elapsed time in milliseconds: " + output );
  }

  public static void main(String[] args) {

    time(Fibo::fibo,4000);
    time(Fibo::fiboIter,4000);
    time(Fibo::fiboMemo,4000);
  }

}
