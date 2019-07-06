package com.company;

import java.util.*;


public class Maze {
    static class Pos {
        int x;
        int y;
        Pos ant;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pos{x=" + x + ", y=" + y + '}';
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
        LinkedList<Pos> posicions = new LinkedList<>();
        boolean[][] visitats = new boolean[maze.length][maze[0].length];

        int [][] opcions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        posicions.add(new Pos(0, 0));

        Pos act = null;

        while (!posicions.isEmpty()) {
            act = posicions.poll();
            if (act.x==maze.length-1 && act.y == maze[0].length-1){
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

        Pos aux = act;
        System.out.println(aux);
        while ((aux = aux.ant) != null)
            System.out.println(aux);
    }

    private static boolean esValida(Pos aux) {
        return aux.x >= 0 && aux.y >= 0 && aux.x < maze.length && aux.y < maze[0].length && maze[aux.x][aux.y] == 1;
    }
}