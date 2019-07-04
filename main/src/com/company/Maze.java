package com.company;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;


public class Maze {
    static class Pos {
        int x;
        int y;
        public Pos ant;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x &&
                    y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static int[][] maze = {
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1}
    };


    public static void main(String[] args) {
        solve(maze);
    }

    private static void solve(int[][] maze) {
        ArrayDeque<Pos> posicions = new ArrayDeque<>();
        HashSet<Pos> visitats = new HashSet<>();

        int [][] opcions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        posicions.push(new Pos(0, 0));

        Pos result = null;

        while (!posicions.isEmpty()) {
            Pos act = posicions.pop();
            if (act.x==maze.length-1 && act.y == maze[0].length-1)
                result = act;
            for (int[] opcio: opcions) {
                Pos aux = new Pos(act.x + opcio[0], act.y + opcio[1]);

                if(esValida(aux) && !visitats.contains(aux)) {
                    aux.ant = act;
                    posicions.offer(aux);
                }
            }

            visitats.add(act);
        }

        Pos aux;

        aux = result;

        while (aux!=null) {
            System.out.println(aux);
            aux = aux.ant;
        }

    }

    private static boolean esValida(Pos aux) {
        return aux.x >= 0 && aux.y >= 0 && aux.x < maze.length && aux.y < maze[0].length && maze[aux.x][aux.y] == 1;
    }
}