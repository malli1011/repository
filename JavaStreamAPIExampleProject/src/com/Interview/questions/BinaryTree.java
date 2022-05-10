package com.Interview.questions;

public class BinaryTree {

    Node root;

    public BinaryTree() {
    }

    public BinaryTree(int val) {
        root = new Node(val, null, null);
    }

    public void add(int val) {
        root = addRecursive(root, val);
    }

    private Node addRecursive(Node current, int val) {
        if (current == null) {
            current = new Node(val, null, null);
            return current;
        }

        if (val < current.value) {
            current.leftNode = addRecursive(current.leftNode, val);
        } else if (val > current.value) {
            current.rightNode = addRecursive(current.rightNode, val);
        }
        return current;
    }


    public void display() {
        display(root, 0,'r');
    }

    private void display(Node current, int i,char c) {
        i++;
        if (current != null) {
            System.out.println("_".repeat(i)+c+current.value);
            if (current.leftNode != null) {
                display(current.leftNode, i,'l');
            }
            if (current.rightNode != null) {
                display(current.rightNode, i,'r');
            }
        }

    }
}
