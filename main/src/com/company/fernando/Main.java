package com.company.fernando;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //Solution s = compute(new int[]{1, 2, 7}, 16);
        Solution s = compute(new int[]{1, 2, 3, 4, 10, 40, 30, 50, 23, 14, 15, 76}, 960);
        System.out.println(s.solution == null ? "No solution found" : s.solution.expression);

        //If there's no solution, a search for the most aprox. value can be found.
    }

    private static Solution compute(int[] input, int objective) {
        return compute(split(input), objective);
    }

    /**
     *
     * @param input
     * @return  Given an int array, splits it in the middle and gets all the permutations
     *          [1,2,3] -> [1,2][3] : [3,2][1] : [1,3][2]
     */
    private static List<Node> split(int[] input){
        int pivot = (input.length % 2 == 0 ? input.length / 2 : input.length / 2 + 1);

        int[] left = new int[pivot];
        for(int i = 0; i < pivot; i++)
            left[i] = input[i];

        int[] right = new int[input.length - pivot];
        for(int i = pivot; i < input.length; i++)
            right[i-pivot] = input[i];

        Node n = new Node(left, right);
        List<Node> result = new ArrayList<>();
        result.add(n);

        if(n.left.length > 1) {
            //if only one item per side, no permutation needed (repetition avoidance)
            for (int i = 0; i < n.left.length; i++) {
                for (int j = 0; j < n.right.length; j++) {
                    Node n2 = n.clone();

                    int aux = n2.left[i];
                    n2.left[i] = n2.right[j];
                    n2.right[j] = aux;

                    result.add(n2);
                }
            }
        }

        return result;
    }

    /**
     * Recursive calls for left and right parts of each node; then computes all the operations (+;-;*;/) for each returned value.
     * @param input
     * @param objective
     * @return
     */
    private static Solution compute(List<Node> input, int objective){
        Solution result = new Solution();

        for(Node n : input){
            if(n.right.length == 0){
                //base case
                Partial p = new Partial(n.left[0]);
                result.partials.add(p);

                //solution found on single number
                if(n.left[0] == objective) result.solution = p;
                return  result; //abort execution, solution found! :)
            }
            else {
                Solution ls = compute(split(n.left), objective);
                if (ls.solution != null) return ls;

                Solution rs = compute(split(n.right), objective);
                if (rs.solution != null) return rs;

                //computing solutions between each other
                for (Partial pl : ls.partials) {
                    for (Partial pr : rs.partials) {

                        //LEFT + RIGHT
                        int r = pl.value + pr.value;
                        Partial pn = new Partial(r, String.format("(%s+%s)", pl.expression, pr.expression));
                        if(r > 0 ) result.partials.add(pn);
                        if(r == objective) result.solution = pn;

                        //LEFT - RIGHT
                        r = pl.value - pr.value;
                        pn = new Partial(r, String.format("(%s-%s)", pl.expression, pr.expression));
                        if(r > 0 ) result.partials.add(pn);
                        if(r == objective) result.solution = pn;

                        //RIGHT - LEFT
                        r = pr.value - pl.value;
                        pn = new Partial(r, String.format("(%s-%s)", pr.expression, pl.expression));
                        if(r > 0 ) result.partials.add(pn);
                        if(r == objective) result.solution = pn;

                        //LEFT * RIGHT
                        r = pl.value * pr.value;
                        pn = new Partial(r, String.format("(%s*%s)", pl.expression, pr.expression));
                        if(r > 0 ) result.partials.add(pn);
                        if(r == objective) result.solution = pn;

                        //LEFT / RIGHT
                        r = pl.value / pr.value;
                        pn = new Partial(r, String.format("(%s/%s)", pl.expression, pr.expression));
                        if(r > 0 && pl.value % pr.value == 0) result.partials.add(pn);
                        if(r == objective) result.solution = pn;

                        //RIGHT / LEFT
                        r = pr.value / pl.value;
                        pn = new Partial(r, String.format("(%s/%s)", pr.expression, pl.expression));
                        if(r > 0  && pr.value % pl.value == 0) result.partials.add(pn);
                        if(r == objective) result.solution = pn;

                        //ABORT if solution found
                        if(result.solution != null) return  result;
                    }
                }
            }
        }

        return  result;
    }

}
