package com.company.fernando;
public class Node {
    public int[] left;
    public int[] right;

    public Node(int[] left, int[] right){
        this.left = left;
        this.right = right;
    }

    public Node clone(){
        int[] left = new int[this.left.length];
        for(int i = 0; i < this.left.length; i++)
            left[i] = this.left[i];

        int[] right = new int[this.right.length];
        for(int i = 0; i < this.right.length; i++)
            right[i] = this.right[i];

        return new Node(left, right);
    }
}
