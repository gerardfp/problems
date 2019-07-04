package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

public class XyL {
//    static int[] n = {2,3,4,5};
//    static int p = 49;

    static int[] o = {'+','-','*','/'};
    static HashMap<Character, BiFunction<Integer, Integer, Double>> operators;
    static int[] n = {1,2,3,4};
    static int p = 12;
    static int a = 0;        // el resultat o el mes aproximat, per sota
    static String ops = "";  // ops = a

    static class Expr {
        String e; int v;
        public Expr(String e, int v) { this.e = e; this.v = v; }
    }

    public static void main(String[] args) {
        operators = new HashMap<>();

        operators.put('+', (Integer o1, Integer o2) ->  o1 + (double) o2);
        operators.put('*', (Integer o1, Integer o2) -> o1 * (double) o2);
        operators.put('-', (Integer o1, Integer o2) -> o1 - (double) o2);
        operators.put('/', (Integer o1, Integer o2) -> o1 / (double) o2);

        List<Integer> d = new ArrayList<>();
        for (int i = 0; i < n.length; i++) d.add(n[i]);

        List<Expr> exprs = new ArrayList<>();
        permute(new ArrayList<>(), d, exprs);

        exprs.forEach(e -> {if(e.v==p) System.out.println(e.e +"  =  "+e.v);});
    }

    static void permute(List<Integer> l, List<Integer> r, List<Expr> exprs){
        List<Expr> exprList = xil(l);
        exprs.addAll(exprList);

//        if(a == p) return;

        List<Integer> hl = new ArrayList<>(l);
        List<Integer> hr = new ArrayList<>(r);
        for (int i = 0; i < r.size(); i++) {
            int val = hr.remove(i);
            hl.add(0,val);
            permute(hl, hr, exprs);
            hr.add(i, val);
            hl.remove(0);
        }
    }

    private static List<Expr> xil(List<Integer> d) {
        if(d.size() == 1){
            List<Expr> exprs = new ArrayList<>();
            exprs.add(check(new Expr(""+d.get(0), d.get(0))));
            return exprs;
        }

        List<Expr> exprs = new ArrayList<>();
        for (int i = 1; i < d.size(); i++) {
            List<Expr> exprsLeft = xil(d.subList(0,i));
            List<Expr> exprsRight = xil(d.subList(i, d.size()));

            for(Expr exprLeft: exprsLeft){
                for(Expr exprRight: exprsRight){
                    for(Character op : operators.keySet()){
                        double val = operators.get(op).apply(exprLeft.v, exprRight.v);
                        if(val >= 0 && val == (int) val){
                            exprs.add(check(new Expr(String.format("(%s%s%s)",exprLeft.e, op, exprRight.e), (int) val)));
//                            if(p == a) return new ArrayList<>();
                        }
                    }
                }
            }
        }
        return exprs;
    }

    static Expr check(Expr e){
        if(e.v <= p && e.v > a){
            ops = e.e;
            a = e.v;
        }
        return e;
    }
}
