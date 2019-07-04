package com.company;


public class KnapsackExacta {

    static int capacity = 6;
    static int[] values = {10,20,30};
    static int[] weights = {2,3,5};
    static int[] nitems = {2,1,1};
    static int[] picklist = {0,0,0};

    public static void main(String[] args) {
        System.out.println(exactRecursive(values.length, capacity, 3));
    }

    public static int exactRecursive(int i, int c, int n){
        if(i==0 || c==0 || n==0) return 0;

        if(weights[i-1] > c){
            return exactRecursive(i-1, c, n);
        }
        return Math.max(exactRecursive(i-1, c, n), exactRecursive(i-1, c-weights[i-1], n-1));
    }
}
