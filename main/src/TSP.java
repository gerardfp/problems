public class TSP {

    public static void main(String[] args) {
        int[] prices = {1,5,6};
        System.out.println(tsp(prices, 8));
    }

    static int tsp(int[] prices, int size){
        int[] K = new int[size+1];

        for (int i = 1; i <= size; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i && j < prices.length; j++) {
                max = Math.max(max, prices[j] + K[i-j-1]);
            }
            K[i] = max;
        }
        return K[size];
    }
}

/*
 * https://www.geeksforgeeks.org/travelling-salesman-problem-set-1/
 * Given a set of cities and distance between every pair of cities, the problem is to find the shortest possible route
 * that visits every city exactly once and returns to the starting point.
 */