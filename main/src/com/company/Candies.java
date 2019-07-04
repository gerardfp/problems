package com.company;

/*
GREEDY


Alice is a kindergarten teacher. She wants to give some candies to the children in her class.
All the children sit in a line and each of them has a rating score according to his or her
performance in the class.  Alice wants to give at least 1 candy to each child.
If two children sit next to each other, then the one with the higher rating must get more candies.
Alice wants to minimize the total number of candies she must buy.

For example, assume her students' ratings are [4, 6, 4, 5, 6, 2].
She gives the students candy in the following minimal amounts: [1, 2, 1, 2, 3, 1].
She must buy a maximum of 10 candies.
 */

public class Candies {

    static int[] notes = {4,2,3,4,5,1,6,3};

    public static void main(String[] args) {

//        notes = new int[100000];
//
//        for (int i = 0; i < notes.length; i++) {
//            notes[i] = i+1;
//        }

        System.out.println(candies(notes.length, notes));
    }

    static long candies(int n, int[] arr){
        int[] cand = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            cand[i] = 1;
        }

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > arr[i-1]) {
                cand[i] = cand[i-1]+1;
            }
        }

        for(int i = arr.length-2; i >= 0; i--) {
            if(arr[i] > arr[i+1]) {
                cand[i] = Math.max(cand[i], cand[i+1]+1);
            }
        }

        long count = 0;
        for(int i = 0; i < cand.length; i++) {
            count = count + cand[i];
        }
        return count;
    }
}
