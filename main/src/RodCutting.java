import java.util.Arrays;

public class RodCutting{
    static int cutRod(int[] prices, int size){
        int[] maxs = new int[size+1];

        for (int i = 1; i < maxs.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i && j < prices.length; j++) {
                max = Math.max(max, prices[j] + maxs[i-j-1]);
            }
            maxs[i] = max;
        }
        return maxs[maxs.length-1];
    }

    public static void main(String[] args) {
	    int[] prices = {1,5,6};
        System.out.println(cutRod(prices, 8));
    }
}