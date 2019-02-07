package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProfesorMusica {
    static Scanner sc;

    static {
        try {
            sc = new Scanner(new File("p391"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static int[][] rs;     // all roads, adjacencia
    static int[] towns;    // asigned towns

    public static void main(String[] args) {


        while(sc.hasNextInt()){
            int nt = sc.nextInt(); // num of towns
            int nr = sc.nextInt(); // num of roads

            rs = new int[nt][nt];

            for (int i = 0; i < nr; i++) {
                int st = sc.nextInt();
                int dt = sc.nextInt();
                int km = sc.nextInt();
                if(st != dt) {
                    if(rs[st-1][dt-1] ==0 || rs[st-1][dt-1] > km) {
                        rs[st - 1][dt - 1] = km;
                        rs[dt - 1][st - 1] = km;
                    }
                }
            }

            int c = sc.nextInt();       // cursos
            for (int i = 0; i < c; i++) {
                int at = sc.nextInt();  // assigned towns
                towns = new int[at];

                for (int j = 0; j < at; j++) {
                    towns[j] = sc.nextInt();
                }

                optimiza();
            }
        }
    }

    static void optimiza(){
        // tengo un monton de roads y unos towns asignados
        // devolver: donde mudarse   <= no puede estar en towns
        //           minimo kms para visitar towns

        Util.printMatrix(rs);

    }
}

/*
 * https://www.aceptaelreto.com/problem/statement.php?id=391
 */