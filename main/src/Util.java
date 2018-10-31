public class Util {

    static void printMatrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%3d ", m[i][j]);
            }
            System.out.println();
        }
        System.out.println("----");
    }

    static void printArray(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%3d", a[i]);

        }
        System.out.println("----");
    }
}
