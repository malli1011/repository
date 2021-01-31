package com.practice;

public class Node<T> {

    T val;
    Node<T> left;
    Node<T> right;

    public Node(T val) {
        this.val = val;
        left = null;
        right = null;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
