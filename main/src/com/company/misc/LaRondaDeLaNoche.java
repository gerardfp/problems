package com.company.misc;

import java.util.ArrayDeque;
import java.util.Scanner;

/*
https://www.aceptaelreto.com/problem/statement.php?id=385
 */
public class LaRondaDeLaNoche {
    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        for (int n = 0; n < N; n++) {
            int w = scanner.nextInt();
            int h = scanner.nextInt();
            scanner.nextLine();
            char[][] lab = new char[h][];

            int x = 0, y = 0;
            int u = 0, v = 0;
            for (int i = 0; i < h; i++) {
                lab[i] = scanner.nextLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (lab[i][j] == 'E') {
                        y = i;
                        x = j;
                    }else if(lab[i][j] == 'P'){
                        v = i;
                        u = j;
                    }
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (lab[i][j] >= '0' && lab[i][j] <= '9') {
                        int num = Character.getNumericValue(lab[i][j]);
                        lab[i][j] = '&';
                        for (int k = 1; k <= num && j + k < w; k++) {
                            if (lab[i][j + k] == '#') break;
                            if (lab[i][j + k] <= '0' || lab[i][j + k] > '9') lab[i][j + k] = '&';
                        }
                        for (int k = 1; k <= num && j - k >= 0; k++) {
                            if (lab[i][j - k] == '#') break;
                            if (lab[i][j - k] <= '0' || lab[i][j - k] > '9') lab[i][j - k] = '&';
                        }
                        for (int k = 1; k <= num && i + k < h; k++) {
                            if (lab[i + k][j] == '#') break;
                            if (lab[i + k][j] <= '0' || lab[i + k][j] > '9') lab[i + k][j] = '&';
                        }
                        for (int k = 1; k <= num && i - k >= 0; k++) {
                            if (lab[i - k][j] == '#') break;
                            if (lab[i - k][j] <= '0' || lab[i - k][j] > '9') lab[i - k][j] = '&';
                        }
                    }
                }
            }

            int sol = solveBFS(lab, w, h, x, y, u, v);
            if (sol == Integer.MAX_VALUE) {
                System.out.println("NO");
            } else {
                System.out.println(sol);
            }
        }
    }

    static int solveBFS(char[][] lab, int w, int h, int x, int y, int u, int v) {
        boolean[][] visited = new boolean[h][w];
        int[][] distance = new int[h][w];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        if(lab[y][x] == '&' || lab[v][u] == '&') return Integer.MAX_VALUE;

        visited[y][x] = true;
        q.add(new int[]{y, x});
        while (!q.isEmpty()) {
            int[] s = q.poll();

            for (int[] move : moves) {
                int ny = s[0] + move[0];
                int nx = s[1] + move[1];
                if (ny >= 0 && ny < h && nx >= 0 && nx < w) {
                    if (ny == v && nx == u) {
                        return distance[s[0]][s[1]] + 1;
                    }
                    if (lab[ny][nx] == '.' && !visited[ny][nx]) {
                        distance[ny][nx] = distance[s[0]][s[1]] + 1;
                        visited[ny][nx] = true;
                        q.add(new int[]{ny, nx});
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}