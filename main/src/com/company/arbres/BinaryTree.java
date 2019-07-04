package com.company.arbres;

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}

public class BinaryTree {
    public static void main(String[] args) {
        Node tree = null;

        tree = insert(tree, 1);
        tree = insert(tree, 2);
        tree = insert(tree, 4);
        tree = insert(tree, 5);
        tree = insert(tree, 3);

        printPreorder(tree);
    }

    static Node insert(Node tree, int value){
        if(tree == null) return new Node(value);

        if(value < tree.value) tree.left = insert(tree.left, value);
        else if (value > tree.value) tree.right = insert(tree.right, value);

        return tree;
    }

    static void printPreorder(Node tree){
        if(tree == null){
            return;
        }
        System.out.println(tree.value);

        printPreorder(tree.left);
        printPreorder(tree.right);
    }
}