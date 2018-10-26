import java.util.Arrays;

public class RodCutting{

    static int cutRod(int price[], int n) {
        int val[] = new int[n+1];
        val[0] = 0;
        int sel[] = new int[n+1];

        for (int i = 1; i<=n; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                int new_max = price[j] + val[i - j - 1];
                if(new_max > max_val){
                    max_val = new_max;
                    sel[i] = j+1;
                }
            }
            val[i] = max_val;
        }

        System.out.println(Arrays.toString(val));
        System.out.println(Arrays.toString(sel));
        int length = n+1;
        while(sel[length-1] > 0){
            System.out.println(sel[length-1]);
            length -= sel[length-1];
        }
        return val[n];
    }

    public static void main(String args[]) {
        int price[] = new int[] {2, 5, 8, 9, 10, 17, 17, 20};
        System.out.println("Maximum Obtainable Value is " + cutRod(price, price.length));
    }
}