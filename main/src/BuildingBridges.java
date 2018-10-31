public class BuildingBridges {

    /* La idea es trobar la secuencia ascendent més llarga
    *
    *  {8,1,4,3,5,2,6,7}
    *  1 < 4
    *  3 < 5
    *  2 < 6 < 7  ===> 3 ponts
    * */

    static int maxBridges(int[] n, int[]s){
        int[] K = new int[n.length];

        K[0] = 1;
        for (int i = 1; i < n.length; i++) {
            if(n[i] >= n[i-1]){
                K[i] = K[i-1] + 1;
            }else{
                K[i] = 1;
            }
        }

        int max = 0;
        for (int i = 0; i < K.length; i++) {
            max = Math.max(max, K[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] north = {8,8,9,1,2,3,3,4};
        int[] south = {1,2,3,4,5,6,7,8};

        System.out.println(maxBridges(north, south));
    }
}
