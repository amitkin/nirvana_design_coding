package com.mylearning.bst;

import static java.lang.Math.abs;
import static java.lang.Math.max;

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
        if(root == null)
            return null;

        BinaryTreeNode left = mirrorCopyRec(root.left);
        BinaryTreeNode right = mirrorCopyRec(root.right);

        root.left =  right;
        root.right = left;

        return root;
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

    public boolean isThisABST(){
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /* Returns true if the given tree is a BST and its
      values are >= min and <= max. */
    private boolean isBSTUtil(BinaryTreeNode root, int min, int max){
        if(root == null)
            return true;
        if(root.data < min || root.data > max)
            return false;
        return (isBSTUtil(root.left, min, root.data-1) && isBSTUtil(root.right, root.data+1, max));
    }

    public boolean isBalanced() {
        return isBalancedUtil(root) != -1;
    }

    private int isBalancedUtil(BinaryTreeNode root) {
        if (root == null) return 0;

        int leftHeight = isBalancedUtil(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = isBalancedUtil(root.right);
        if (rightHeight == -1) return -1;

        if (abs(leftHeight - rightHeight) > 1)  return -1;
        return 1 + max(leftHeight, rightHeight);
    }

    public int largestBSTSubtree() {
        return largestBSTSubtreeUtil(root).size;
    }

    public LargestBstWrapper largestBSTSubtreeUtil(BinaryTreeNode node){
        LargestBstWrapper curr = new LargestBstWrapper();

        if(node == null){
            curr.isBST= true;
            return curr;
        }

        LargestBstWrapper l = largestBSTSubtreeUtil(node.left);
        LargestBstWrapper r = largestBSTSubtreeUtil(node.right);

        //current subtree's boundaries
        curr.lower = Math.min(node.data, l.lower);
        curr.upper = Math.max(node.data, r.upper);

        //check left and right subtrees are BST or not
        //check left's upper again current's value and right's lower against current's value
        if(l.isBST && r.isBST && l.upper<=node.data && r.lower>=node.data){
            curr.size = l.size+r.size+1;
            curr.isBST = true;
        }else{
            curr.size = Math.max(l.size, r.size);
            curr.isBST  = false;
        }
        return curr;
    }

    class LargestBstWrapper{
        int size;
        int lower, upper;
        boolean isBST;

        public LargestBstWrapper(){
            lower = Integer.MAX_VALUE;
            upper = Integer.MIN_VALUE;
            isBST = false;
            size = 0;
        }
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
        System.out.println("isBalanced : " + tree.isBalanced());
        System.out.println("Largest BST Subtree : " + tree.largestBSTSubtree());
    }
}