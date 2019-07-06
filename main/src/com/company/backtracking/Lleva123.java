package com.company.backtracking;

public class Lleva123 {


    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(guanye(i, true));
        }
//        System.out.println(guanye(4,true));
    }

    static boolean guanye(int n, boolean yo){
        if(n<=0 && yo) return false;
        if(n<=0) return true;

        boolean guanye = !yo;

        for (int i = 1; i <= 3 && i<=n; i++) {
            if(yo){
                guanye = guanye || guanye(n-i, false);
            }else{
                guanye = guanye && guanye(n-i, true);
            }
        }

        return guanye;
    }
}
