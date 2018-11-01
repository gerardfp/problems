public class Riu {

    /* http://www3.uji.es/~vjimenez/AULASVIRTUALES/AED-1718/#ejercicios53  [17]
    *
     */
    static int minCost(int[][] c){

        int[] K = new int[c.length];

        for (int i = 1; i < c.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                min = Math.min(min, K[j] + c[j][i]);
            }
            K[i] = min;
        }

        return K[c.length-1];
    }

    public static void main(String[] args) {
        int[][] costes = {
                {0, 5, 8, 13},
                {0, 0, 4, 5},
                {0, 0, 0, 3},
                {0, 0, 0, 0}
        };

        System.out.println(minCost(costes));
    }

}
