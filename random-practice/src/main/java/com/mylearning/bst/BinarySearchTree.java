package com.mylearning.bst;

import java.util.LinkedList;
import java.util.Queue;

/*
In a Binary Search Tree (BST), all keys in left subtree of a key must be smaller
and all keys in right subtree must be greater.
*/
public class BinarySearchTree {
    private BinaryTreeNode root;

    private BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private BinaryTreeNode insertRec(BinaryTreeNode root, int data) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new BinaryTreeNode(data);
        } else {
            /* Otherwise, recur down the tree */
            if (data < root.data)
                root.left = insertRec(root.left, data);
            else if (data > root.data)
                root.right = insertRec(root.right, data);
        }
        /* return the (unchanged) node pointer */
        return root;
    }

    public void inorder(){
        inorderRec(root);
    }

    private void inorderRec(BinaryTreeNode root){
        if(root != null){
            inorderRec(root.left);
            System.out.print(root.data + " -> ");
            inorderRec(root.right);
        }
    }

    public int size(){
        return sizeRecur(root);
    }

    private int sizeRecur(BinaryTreeNode root){
        if(root == null)
            return 0;
        else{
            return 1 + sizeRecur(root.left) + sizeRecur(root.right);
        }
    }

    public int maxDepth(){
        return maxDepthRec(root);
    }

    public boolean find(int data){
        return findRec(root, data);
    }

    private boolean findRec(BinaryTreeNode root, int data){
        if(root == null)
            return false;
        else
        {
            if(data==root.data)
                return true;
            else
            {
                if (data < root.data)
                    return(findRec(root.left, data));
                else
                    return(findRec(root.right, data));
            }
        }
    }

    //DFS
    private int maxDepthRec(BinaryTreeNode root){
        if(root == null)
            return 0;
        else{
            int lDepth = 1 + maxDepthRec(root.left);
            int rDepth = 1 + maxDepthRec(root.right);
            if(lDepth > rDepth)
                return lDepth;
            else
                return rDepth;
        }
    }

    public int minValue(){
        return minValueRec(root);
    }

    private int minValueRec(BinaryTreeNode root){
        if(root == null)
            return -1;
        else{
            while(root.left != null)
                root = root.left;
            return root.data;
        }
    }

    private int findParent(int i, int j){
        return findParentRec(root, i, j);
    }

    //Java manipulates objects 'by reference,' but it passes object references to methods 'by value.'
    //As a result, you cannot write a standard swap method to swap objects.
    //Below if you change the root.data it will reflect but if you do root = root.left nothing happens outside.
    private int findParentRec(BinaryTreeNode root, int i, int j){
        while(true){
            if(root.data>i && root.data>j)
                root = root.left;
            else if(root.data<i && root.data<j)
                root = root.right;
            else
                return root.data;
        }
    }

    void mirror(){
        mirrorUtil(root);
    }

    void mirrorUtil(BinaryTreeNode node){
        if(node == null)
            return;
        if(node.left == null && node.right == null)
            return;
        mirrorUtil(node.left);
        mirrorUtil(node.right);
        BinaryTreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    public BinaryTreeNode mirrorCopy(){
        return mirrorCopyRec(root);
    }

    private BinaryTreeNode mirrorCopyRec(BinaryTreeNode root){
        //Important to create temp BinaryTreeNode to save the swapped nodes
        BinaryTreeNode temp;
        if(root.left == null && root.right == null)
            return root;
        else{
            temp = new BinaryTreeNode(root.data);
            temp.left = mirrorCopyRec(root.right);
            temp.right = mirrorCopyRec(root.left);
            return temp;
        }
    }

    public boolean isIdentical(BinaryTreeNode a, BinaryTreeNode b){
        return isIdenticalRec(a, b);
    }

    private boolean isIdenticalRec(BinaryTreeNode a, BinaryTreeNode b){
        if(a == null && b == null)
            return true;
        else{
            if(a.data != b.data)
                return false;
            return isIdenticalRec(a.left, b.left) && isIdenticalRec(a.right, b.right);
        }
    }

    public void levelOrderTraversal(){
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            /* poll() - Removes and returns the head of the queue. Returns null if queue is empty.
            remove()-Removes and returns the head of the queue. Throws NoSuchElementException when queue is impty.
            */
            BinaryTreeNode tempNode = queue.poll();
            if(tempNode != null) {
                System.out.print(tempNode.data + " ");

                /*Enqueue left child */
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }

                /*Enqueue right child */
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
        }
    }

    private boolean isThisABST(){
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /* Returns true if the given tree is a BST and its
      values are >= min and <= max. */
    public boolean isBSTUtil(BinaryTreeNode root, int min, int max){
        if(root == null)
            return true;
        if(root.data < min || root.data > max)
            return false;
        return (isBSTUtil(root.left, min, root.data-1) && isBSTUtil(root.right, root.data+1, max));
    }

    public class BinaryTreeNode {
        public int data;
        public BinaryTreeNode left, right;

        private BinaryTreeNode(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // Driver Program to test above functions
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
                  50
               /     \
              30      70
             /  \    /  \
           20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.inorder();
        System.out.println("\nSize of Tree : " + tree.size());
        System.out.println("Max Depth of Tree : " + tree.maxDepth());
        System.out.println("Found node with data 70 : " + tree.find(25));
        System.out.println("Min value in tree : " + tree.minValue());
        System.out.println("Parent of 20 and 40 is : " + tree.findParent(20, 40));
        tree.mirror();
        tree.inorder();
        tree.mirror();
        System.out.println();
        System.out.println("After mirrorcopy trees are identical : " + tree.isIdentical(tree.mirrorCopy(), tree.mirrorCopy()));
        tree.levelOrderTraversal();
        System.out.println();
        System.out.println("isThisABST : " + tree.isThisABST());
    }
}