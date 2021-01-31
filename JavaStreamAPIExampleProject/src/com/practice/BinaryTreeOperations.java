package com.practice;

//Java implementation to print largest
// value in each level of Binary Tree  

import java.util.*;

class BinaryTreeOperations {

    // structure of a node of binary tree
    static class Node {
        int data;
        Node left = null;
        Node right = null;
    }

    // function to get a new node
    static Node newNode(int val) {
        // allocate space
        Node temp = new Node();

        // put in the data
        temp.data = val;
        temp.left = null;
        temp.right = null;
        return temp;
    }

    // function to print largest value
    // in each level of Binary Tree
    static Map<Integer, Integer> largestValueInEachLevel(Node root) {
        Map<Integer, Integer> highestValues = new HashMap<>();
        Integer level = 0;

        // if tree is empty
        if (root == null)
            return highestValues;

        Queue<Node> q = new LinkedList<Node>();
        int nc, max;

        // push root to the queue 'q'
        q.add(root);

        while (true) {
            // node count for the current level
            nc = q.size();

            // if true then all the nodes of
            // the tree have been traversed
            if (nc == 0)
                break;

            // maximum element for the current
            // level
            max = Integer.MIN_VALUE;

            while (nc != 0) {

                // get the front element from 'q'
                Node front = q.peek();

                // remove front element from 'q'
                q.remove();

                // if true, then update 'max'
                if (max < front.data)
                    max = front.data;

                // if left child exists
                if (front.left != null)
                    q.add(front.left);

                // if right child exists
                if (front.right != null)
                    q.add(front.right);
                nc--;
            }

            // print maximum element of
            // current level
            highestValues.put(level, max);
            level++;
        }
        return highestValues;
    }

    static int heightOfBinaryTree(Node root) {
        int height = 0;
        if (root == null) {
            return height;
        }
        int nc;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (true) {
            nc = queue.size();

            if (nc == 0) {
                break;
            }
            while (nc != 0) {
                Node node = queue.peek();
                queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                nc--;
            }
            if (queue.size() > 0) {
                height++;
            }

        }
        return height;
    }

    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        List<Integer> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int nc;

        while (true) {
            nc = queue.size();
            if (nc == 0)
                break;
            while (nc != 0) {
                Node node = queue.peek();
                queue.remove();
                nodes.add(node.data);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                nc--;
            }
        }
        nodes.forEach(e -> System.out.print(" -> "+e));

    }

    // Driver program to test above
    public static void main(String[] args) {
    /* Construct a Binary Tree  
        4  
    /   
    9 2  
    /   
    3 5 7 */

        Node root = null;
        root = newNode(4);
        root.left = newNode(9);
        root.right = newNode(2);
        root.left.left = newNode(3);
        root.left.right = newNode(5);
        root.right.right = newNode(7);
        root.left.left.right = newNode(3);
        root.left.left.right.right = newNode(5);

        System.out.println(heightOfBinaryTree(root));
        levelOrder(root);
    }
}  