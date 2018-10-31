public class BuildingBridges {

    /* La idea es trobar la secuencia ascendent més llarga
    *
    *  {8,1,4,3,5,2,6,7}
    *  1 < 4
    *  3 < 5
    *  2 < 6 < 7  ===> 3 ponts
    *
    * */

    static int maxBridges(int[] n, int[]s){
        // realment no faria falta un array, en una variable prou
        int[] K = new int[n.length];

        K[0] = 1;
        int max = 1;
        for (int i = 1; i < n.length; i++) {
            if(n[i] >= n[i-1]){
                K[i] = K[i-1] + 1;
                max = Math.max(max, K[i]);
            }else{
                K[i] = 1;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] north = {8,8,9,1,2,2,1,3,3,4};
        int[] south = {1,2,3,4,5,6,7,8};

        System.out.println(maxBridges(north, south));
    }
}
