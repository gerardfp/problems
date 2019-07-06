package com.company.backtracking;

import java.util.Scanner;

public class Gangplank {
    static float size; // = 29.30f;
    static int npistols; // = 2;

    static float [] factors; // = {0.3f, 0.7f, 0.43f, 0.54f };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ncasos = scanner.nextInt();

        for (int i = 0; i < ncasos; i++) {
            size = Float.parseFloat(scanner.next());
            npistols = scanner.nextInt();
            factors = new float[npistols];

            for (int j = 0; j < npistols; j++) {
                factors[j] = Float.parseFloat(scanner.next());
            }
            System.out.println(ganoYo(size) ? "JO" : "GANGPLANK");
        }
    }

    static boolean ganoYo(float size){
        for (int i = 0; i < factors.length; i++) {
           if(size*factors[i] <= 1) return true;
        }

        boolean ganael = false;
        for (int i = 0; i < factors.length; i++) {
            ganael = ganael || !ganaEl(size*factors[i]);
        }

        return ganael;
    }

    static boolean ganaEl(float size){
        for (int i = 0; i < factors.length; i++) {
            if(size*factors[i] <= 1) return true;
        }

        boolean ganoyo = true;
        for (int i = 0; i < factors.length; i++) {
            ganoyo = ganoyo && !ganoYo(size*factors[i]);
        }

        return ganoyo;
    }

}
