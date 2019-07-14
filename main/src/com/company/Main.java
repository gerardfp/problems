package com.company;

public class Main {

    public static void main(String[] args) {
        jaja(10);
    }

    static void jaja(int n){
        jaja(n-1);
        System.out.println(n);
    }
}
