package com.company;

import java.util.*;


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
        System.out.println("--------------------");
        solve2(maze);
    }

    private static void solve(int[][] maze) {
        LinkedList<Pos> posicions = new LinkedList<>();
        boolean[][] visitats = new boolean[maze.length][maze[0].length];

        int [][] opcions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        posicions.add(new Pos(0, 0));

        Pos result = null;

        while (!posicions.isEmpty()) {
            Pos act = posicions.poll();
            if (act.x==maze.length-1 && act.y == maze[0].length-1){
                result = act;
                break;
            }
            for (int[] opcio: opcions) {
                Pos next = new Pos(act.x + opcio[0], act.y + opcio[1]);

                if(esValida(next) && !visitats[next.x][next.y]) {
                    next.ant = act;
                    posicions.add(next);
                }
            }

            visitats[act.x][act.y] = true;
        }

        Pos aux = result;
        while (aux!=null) {
            System.out.println(aux);
            aux = aux.ant;
        }
    }

    private static void solve2(int[][] maze) {
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

        Pos aux = result;
        while (aux!=null) {
            System.out.println(aux);
            aux = aux.ant;
        }
    }

    private static boolean esValida(Pos aux) {
        return aux.x >= 0 && aux.y >= 0 && aux.x < maze.length && aux.y < maze[0].length && maze[aux.x][aux.y] == 1;
    }
}