package com.company;

import java.util.Random;

public class JobScheduling {
    static int[][] jobs = {
            {3,100,200},
            {3,5,20},
            {6,19,100},
            {1,2,50},
            {2,3,10}
    };

    public static void main(String[] args) {
        System.out.println(recursiu(0,jobs));
    }

    private static int recursiu(int fi, int[][] jobs) {
        int max = 0;
        for (int i = 0; i < jobs.length; i++)
            if(jobs[i][0] >= fi)
                max = Math.max(max, recursiu(jobs[i][1], jobs) + jobs[i][2]);
        return max;
    }

    static void fake(){
        Random random = new Random();

        jobs = new int[random.nextInt(5)+1][3];

        for (int i = 0; i < jobs.length; i++) {
            int ini = random.nextInt(500);
            int fi = random.nextInt(500)+ini+1;
            jobs[i] = new int[]{ini, fi, random.nextInt(500)};
        }
    }

    static boolean overlap(int[] a, int[] b){
        if(b[0] >= a[1] || b[1] <= a[0]) return false;
        return true;
    }
}
