package com.company.fernando;
public class Partial {
    public String expression;
    public int value;

    public Partial(int value){
        this.value = value;
        this.expression = String.valueOf(value);
    }

    public Partial(int value, String expression){
        this.value = value;
        this.expression = expression;
    }
}
