package com.company;

import java.util.Scanner;

/*
GREEDY

Christy is interning at HackerRank. One day she has to distribute some chocolates to her colleagues.
She is biased towards her friends and plans to give them more than the others.
One of the program managers hears of this and tells her to make sure everyone gets the same number.

To make things difficult, she must equalize the number of chocolates in a series of operations.
For each operation, she can give 1, 2 or 5 chocolates to all but one colleague.
Everyone who gets chocolate in a round receives the same number of pieces.

For example, assume the starting distribution is [1,1,5].
She can give 2 bars to the first two and the distribution will be [3,3,5].
On the next round, she gives the same two 2 bars each, and everyone has the same number: [5,5,5].

Given a starting distribution, calculate the maximum number of operations needed
so that every colleague has the same number of chocolates.
 */
public class EqualChocolates {



    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int ncases = scanner.nextInt();
        int[] arr = new int[ncases];
        for (int i = 0; i < ncases; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(equal(arr));
    }

    static int equal(int[] arr) {
        int[] ops = {1, 2, 5};

        int min = smallest(arr);
       int[] sum = new int[6];
        for (int i = 0; i <= 5; i++) {
            sum[i]=0;
            for (int j = 0; j < arr.length; j++) {
                int temp = mod(arr[j] - (min-i));
                sum[i] = sum[i] + functn(temp);
            }
        }

        return smallest(sum);
    }

    static int functn(int temp){
        int x=0;
        if(temp>=5){
            x= temp/5;
            temp = temp%5;
        }
        if(temp>=2){
            x+=temp/2;
            temp = temp%2;
        }
        x += temp;
        return x;
    }

    static int smallest(int[] array){
        int small = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if(array[i] < small) small = array[i];
        }
        return small;
    }

    static int mod(int x){
        if (x >0) return x;
        return -1*x;
    }

}
