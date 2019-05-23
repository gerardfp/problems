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

    public static void main(String[] args) {
        // read data
        while(sc.hasNextInt()){
            int nt = sc.nextInt(); // num of as
            int nr = sc.nextInt(); // num of roads

            int[][] rs = new int[nt][nt];  // all roads, adjacencia

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
                int at = sc.nextInt();  // num assigned
                int[] as = new int[at];  // assigned

                for (int j = 0; j < at; j++) {
                    as[j] = sc.nextInt()-1;
                }

                optimiza(rs, as);
            }
        }
    }

    static void optimiza(int[][] rs, int[] as){
        // input: tengo un monton de carreteras "rs" y unos pueblos asignados "as" que hay que visitar
        // problem: minima ruta que visite todos los asignados, emepezando y acabando en un pueblo que no asignado
        // devolver: donde empieza y kilometros totales minimos

        // primer calcular el minim tsp començant per cada un dels asignats, i guardarlos
        // calcular els shortestPath entre els assignats, i despés aplicar el TSP a ixos shortestPath


        // despres provar desde cada poble, el shortestPath a cada un dels asignat i sumarli el minim tsp desde ixe poble
        // Fer poda.

        // 1 shortestPath entre el "rs"
        // primer fer una matriu de adjacencia amb les "as" i el poble més proper a cada "as"
        int[][] shortestPaths = floydWarshall(rs);

        int tsp = tsp(rs, as,0);

        Util.printArray(as);
        Util.printMatrix(rs);
    }

    static int[][] floydWarshall(int[][] graph){
        int dist[][] = new int[graph.length][graph.length];
        int i, j, k;

        for (i = 0; i < graph.length; i++)
            for (j = 0; j < graph.length; j++)
                dist[i][j] = graph[i][j];

        for (k = 0; k < graph.length; k++) {
            for (i = 0; i < graph.length; i++) {
                for (j = 0; j < graph.length; j++) {
                    if (i != k && j != k && i != j) {
                        //Per a anar de i->j  mirem si es millor anar de i->k i de k->j
                        //System.out.println("i=" + i + "  k=" + k + "  j=" + j);
                        if (dist[i][k] * dist[k][j] > dist[i][j]) {
                            dist[i][j] = dist[i][k] * dist[k][j];
                        }
                    }
                }
            }
        }
        return dist;
    }

    static int tsp(int[][] rs, int[] as, int n){

        for (int i = 0; i < as.length; i++) {

        }
        return 0;
    }
}

/*
 * https://www.aceptaelreto.com/problem/statement.php?id=391
 *
 * Si té quatre pobles asignats
1,2,3,4
1,2,4,3
1,3,2,4
1,3,4,2
1,4,2,3
1,4,3,2

2,1,3,4
2,1,4,3
2,3,1,4
2,4,1,3

3,1,2,4
3,2,1,4
 *
 */
