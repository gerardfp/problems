package com.company;

/*
Given an array of integers, find the subset of non-adjacent elements with the maximum sum.
Calculate the sum of that subset.

For example, given an array [-2,1,3,-4,5] we have the following possible subsets:

Subset      Sum
[-2, 3, 5]   6
[-2, 3]      1
[-2, -4]    -6
[-2, 5]      3
[1, -4]     -3
[1, 5]       6
[3, 5]       8
Our maximum subset sum is 8

http://hr.gs/3u3n7
 */

public class MaxArraySum {

    static int[] a;
    public static void main(String[] args) {

        a = new int[]{-2,1,3,-4,5};      //8
        a = new int[]{3,7,4,6,5};      //13
        a = new int[]{2,1,5,8,4};      //11
        a = new int[]{3,5,-7,8,10};    //15

        System.out.println(maxSum());
        System.out.println(maxSumRecursive(a.length-1));
    }

    static int maxSum(){
        int[] K = new int[a.length+1];

        K[1] = Math.max(0, a[0]);
        for (int i = 2; i <= a.length ; i++) {
            K[i] = Math.max(K[i-1], K[i-2]+a[i-1]);
        }

        return K[a.length];
    }

    static int maxSumRecursive(int n){
        if(n < 0) return 0;
        return Math.max(maxSumRecursive(n-1), maxSumRecursive(n-2)+a[n]);
    }
}
