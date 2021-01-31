package com.practice;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> {

    private Node<T> root;

    public BinaryTree(T val) {
        root = new Node<>(val);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /*Inorder traversal of binary tree*/

    public void inorderDisplay(Node root){
        if(root ==null)
            return;
        System.out.println(root.val);
        inorderDisplay(root.left);
        inorderDisplay(root.right);
    }

    public List<T> getHighestValueInEachLevel(Node<T> root){
        List<T> highestValues = new ArrayList<>();
        List<Node> childNodes = new ArrayList<>();

        if(root ==null){
            return highestValues;
        }
        highestValues.add(root.val);
        while(true){
            childNodes.add(root.left);
            childNodes.add(root.right);
        }

    }
}
