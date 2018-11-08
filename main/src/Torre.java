public class Torre {

    // TODO: no va, no va

    static int minCost(int[] p, int[] s){
        int[] K = new int[p.length+1];

        for (int i = 1; i < p.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < s.length && i > j; j++) {
                min = Math.min(min, K[i-s[j]]+p[i]-s[j]);
            }
            K[i] = min;
        }
        return K[p.length];
    }

    public static void main(String[] args) {
        int[] p = {1,2,1,2,1,2,1,2};
        int[] s = {2,5,6};

        System.out.println(minCost(p, s));
    }
}
