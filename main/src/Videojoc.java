public class Videojoc {

    static int minObjects(int[] p, int v){
        int[] K = new int[v+1];

        for (int i = 1; i <= v; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < p.length && p[j] <= i; j++) {
                min = Math.min(min, K[i-p[j]]+1);
            }
            K[i] = min;
        }
        Util.printArray(K);
        return K[v];
    }

    public static void main(String[] args) {
        int[] p = {1,3,4,6};

        System.out.println(minObjects(p, 12));
    }
}
