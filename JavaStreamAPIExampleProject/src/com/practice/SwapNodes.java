package com.practice;

public class SwapNodes {
    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }


    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }
        display(node.left);
        System.out.println(node.val);
        display(node.right);

    }

    public static void swapNodes(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null && node.right != null) {
            Node left = node.left;
            node.left = node.right;
            node.right = left;
            swapNodes(node.left);
            swapNodes(node.right);
        } else {
            return;
        }
    }

    public static void main(String... args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);
        display(node);
        swapNodes(node);
        display(node);
    }
}
