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

    static int[][] rs;  // all roads, adjacencia
    static int[] as;    // asigned as

    public static void main(String[] args) {


        while(sc.hasNextInt()){
            int nt = sc.nextInt(); // num of as
            int nr = sc.nextInt(); // num of roads

            rs = new int[nt][nt];

            for (int i = 0; i < nr; i++) {
                int st = sc.nextInt();
                int dt = sc.nextInt();
                int km = sc.nextInt();
                if(st != dt) {
                    if(rs[st-1][dt-1] == 0 || rs[st-1][dt-1] > km) {
                        rs[st - 1][dt - 1] = km;
                        rs[dt - 1][st - 1] = km;
                    }
                }
            }

            int c = sc.nextInt();       // cursos
            for (int i = 0; i < c; i++) {
                int at = sc.nextInt();  // assigned as
                as = new int[at];

                for (int j = 0; j < at; j++) {
                    as[j] = sc.nextInt();
                }

                optimiza();
            }
        }
    }

    static void optimiza(){
        // input: tengo un monton de carreteras "rs" y unos pueblos asignados "as" que hay que visitar
        // problem: minima ruta que visite todos los asignados, emepezando y acabando en un pueblo que no asignado
        // devolver: donde empieza y kilometros totales minimos

        // primer calcular el minim tsp començant per cada un dels asignats, i guardarlos
        // calcular els shortestPath entre els assignats, i despés aplicar el TSP a ixos shortestPath


        // despres provar desde cada poble, el shortestPath a cada un dels asignat i sumarli el minim tsp desde ixe poble
        // Fer poda.

        Util.printMatrix(rs);

    }
}

/*
 * https://www.aceptaelreto.com/problem/statement.php?id=391
 */