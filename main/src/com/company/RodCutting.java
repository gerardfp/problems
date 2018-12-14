package com.company;

public class RodCutting{

    static int cutRod(int[] prices, int size){
        int[] K = new int[size+1];

        for (int i = 1; i <= size; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i && j < prices.length; j++) {
                max = Math.max(max, prices[j] + K[i-j-1]);
            }
            K[i] = max;
        }
        return K[size];
    }

    public static void main(String[] args) {
        int[] prices = {1,5,6};
        System.out.println(cutRod(prices, 8));
    }
}

/*
 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 */