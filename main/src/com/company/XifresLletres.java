package com.company;

import java.util.ArrayList;
import java.util.List;

public class XifresLletres {

    static int[] n = {2,3,4,5};
    static char[] o = {'+','*'}; //,'*','/'};
    static int p = 45;
    static int a = 0;   // resultat mes proxim, per sota
    static String seq = "";
    static List<String> seqs = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> disponibles = new ArrayList<>();
        for (int i = 0; i < n.length; i++) disponibles.add(n[i]);

    	result(0, disponibles,"");

        System.out.println(seq + "  =  "  + a);

        System.out.println("*****");
        seqs.forEach(s -> System.out.println(s));


    }

    static int result(int r, List<Integer> disponibles, String ops){
        if(r == p){
            a = r;
            seq = ops;
            seqs.add(ops);
        }


        int res = 0;
        for (int i = 0; i < disponibles.size(); i++) {
            for (int k = 0; k < disponibles.size(); k++) {
                if(i != k){
                    int val1 = disponibles.remove(i);
                    int val2 = disponibles.remove(k);
                    for (int j = 0; j < o.length; j++) {
                        String nop = "("+val1+o[j]+val2+")";
                        int val3 = 0;
                        if(o[j] == '+') val3 = val1+val2;
                        if(o[j] == '-') val3 = val1-val2;
                        if(o[j] == '*') val3 = val1*val2;
                        if(o[j] == '/') val3 = val1/val2;
                        System.out.println(nop);
                    }
                    disponibles.add(i, val1);
                    disponibles.add(k, val2);
                }
            }
        }
        if(disponibles.size() == 1){
            return disponibles.get(0);
        }

        return res;
    }
}
