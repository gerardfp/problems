package com.company;

public class CakeCutting2 {
    private int x, y;
    private int[][] tarta;

    public CakeCutting2(int[][] tarta) {
        this.tarta = tarta;
        this.x = 0;
        this.y = 0;
    }

    public static void main(String[] args) {
        int[][] tarta = {
                {1,1,0,0},
                {1,0,0,0},
                {0,0,1,1},
                {0,1,1,1}
        };

        CakeCutting2 cutter = new CakeCutting2(tarta);
        System.out.println(cutter.cut(0,tarta.length, 0, tarta[0].length));
    }

    private int cut(int x0, int x1, int y0, int y1) {
        if(esvalida(x0, x1, y0, y1)) return 0;

        int mini=Integer.MAX_VALUE, minj = Integer.MAX_VALUE;
        for (int i = x0+1; i < x1; i++) {
            mini = Math.min(mini, cut(i, x1, y0, y1)+cut(x0,i,y0,y1));
        }

        for (int i = y0+1; i < y1; i++) {
            minj = Math.min(minj, cut(x0, x1, i, y1)+cut(x0,x1,y0,i));
        }

        return Math.min(mini, minj)+1;
    }

    private boolean esvalida(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if(tarta[i][j] != tarta[x1][y1]){
                    return false;
                }
            }
        }
        return true;
    }
}
