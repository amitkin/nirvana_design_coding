package com.mylearning.tree;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mylearning.epi.tree.BstNode;

/*
In a Binary Search Tree (BST), all keys in left subtree of a key must be smaller
and all keys in right subtree must be greater.
*/
public class BinarySearchTree {
    public BinaryTreeNode root;

    public BinarySearchTree() {
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

    //From EPI
    public boolean delete(Integer key) {
        // Find the node with key.
        BinaryTreeNode curr = root, parent = null;
        while (curr != null && curr.data != key){
            parent = curr;
            curr = key < curr.data ? curr.left : curr.right;
        }
        if (curr == null) {
            // There’s no node with key in this tree.
            return false;
        }
        BinaryTreeNode keyNode = curr;
        if (keyNode.right != null) {
            // Find the minimum of the right subtree.
            BinaryTreeNode rKeyNode = keyNode.right ;
            BinaryTreeNode rParent = keyNode ;
            while (rKeyNode.left != null) {
                rParent = rKeyNode ;
                rKeyNode = rKeyNode.left ;
            }
            keyNode.data = rKeyNode.data ;
            // Move links to erase the node.
            if (rParent.left == rKeyNode) {
                rParent.left = rKeyNode.right ;
            } else { // rParent.left != rKeyNode.
                rParent.right = rKeyNode.right ;
            }
            rKeyNode.right = null;
        } else {
            // Update root link if needed.
            if (root == keyNode) {
                root = keyNode.left ;
                keyNode.left = null;
            } else {
                if (parent.left == keyNode) {
                    parent.left = keyNode.left ;
                } else {
                    parent.right = keyNode.left ;
                }
                keyNode.left = null;
            }
        }
        return true ;
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
        if(a == null && b == null) {
            return true;
        } else if(a != null && b != null) {
            return (a.data == b.data) && isIdenticalRec(a.left, b.left) && isIdenticalRec(a.right, b.right);
        }
        // One tree is empty, and the other is not.
        return false;
    }

    public boolean isSymmetric (BinaryTreeNode tree) {
        return tree == null || checkSymmetric (tree.left , tree.right);
    }
    private boolean checkSymmetric (BinaryTreeNode subtree0, BinaryTreeNode subtree1) {
        if (subtree0 == null && subtree1 == null) {
            return true ;
        } else if (subtree0 != null && subtree1 != null) {
            return subtree0.data == subtree1.data && checkSymmetric(subtree0.left , subtree1.right) && checkSymmetric(subtree0.right , subtree1.left);
        }
        // One subtree is empty, and the other is not.
        return false;
    }

    class QItem{
        BinaryTreeNode node;
        int depth;

        QItem(BinaryTreeNode node, int depth){
            this.node  = node;
            this.depth = depth;
        }
    }

    public int minDepth(){
        if(root == null)
            return 0;

        Queue<QItem> queue = new LinkedList<>();
        QItem qItem = new QItem(root, 1);
        queue.offer(qItem);

        while(!queue.isEmpty())
        {
            //remove item from queue
            qItem = queue.poll();
            BinaryTreeNode frontNode = qItem.node;
            int depth = qItem.depth;

            //first leaf node case
            if(frontNode.left == null && frontNode.right == null)
                return depth;

            //If not start adding to queue
            if(frontNode.left!= null){
                qItem.node = frontNode.left;
                qItem.depth = depth + 1;
                queue.offer(qItem);
            }

            if(frontNode.right!= null){
                qItem.node = frontNode.right;
                qItem.depth = depth + 1;
                queue.offer(qItem);
            }
        }
        return 0;
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

    public class BinaryTreeNode {
        public int data;
        public BinaryTreeNode left, right;

        private BinaryTreeNode(int data) {
            this.data = data;
            left = right = null;
        }

        private BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    //0(n) solution since we are traversing all the nodes
    public BinaryTreeNode lca(BinaryTreeNode n1, BinaryTreeNode n2){
        return lcaHelper(root, n1, n2);
    }

    private BinaryTreeNode lcaHelper(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2){
        if(root == null){
            return null;
        }
        if(root == n1 || root == n2){
            return root;
        }

        BinaryTreeNode left = lcaHelper(root.left, n1, n2);
        BinaryTreeNode right = lcaHelper(root.right, n1, n2);

        if(left != null && right != null){
            return root;
        }
        return left != null ? left : right;
    }

    //We descend one level with each iteration, the time complexity is 0(h), where h is height of tree
    public BinaryTreeNode findLCA(BinaryTreeNode n1, BinaryTreeNode n2) {
        BinaryTreeNode temp = root;
        while (temp.data < n1.data || temp.data > n2.data) {
            // Keep searching since temp is outside of [n1, n2].
            while (temp.data < n1.data) {
                temp = temp.right; // LCA must be in temp’s right child.
            }
            while (temp.data > n2.data) {
                temp = temp.left; // LCA must be in temp’s left child.
            }
        }
        // Now, n1.data >= temp.data && n2.data >= temp.data.
        return temp;
    }

    public int sumRootToLeaf() {
        return sumRootToLeafHelper(root , 0);
    }
    private static int sumRootToLeafHelper (BinaryTreeNode root, int partialPathSum) {
        if (root == null) {
            return 0;
        }
        partialPathSum = partialPathSum * 2 + root.data;
        if (root.left == null && root.right == null) { // Leaf.
            return partialPathSum;
        }
        // Non-leaf .
        return sumRootToLeafHelper(root.left, partialPathSum)
                + sumRootToLeafHelper(root.right, partialPathSum);

    }

    public boolean hasPathSum(int targetSum) {
        return hasPathSumHelper (root , 0, targetSum);
    }
    private boolean hasPathSumHelper (BinaryTreeNode root, int partialPathSum , int targetSum) {
        if (root == null) {
            return false;
        }
        partialPathSum += root.data;
        if (root.left == null && root.right == null) { // Leaf.
            return partialPathSum == targetSum;
        }
        // Non-leaf .
        return hasPathSumHelper (root.left , partialPathSum, targetSum)
                || hasPathSumHelper (root.right , partialPathSum, targetSum);
    }

    //Time - O(n), Space - O(h)
    public List<Integer> inOrderIterative() {
        Deque<BinaryTreeNode> s = new LinkedList<>();
        BinaryTreeNode curr = root;
        List<Integer> result = new ArrayList<>();
        while (!s.isEmpty() || curr != null) {
            if (curr != null) {
                s.addFirst(curr);
                curr = curr.left; // Going left
            } else {
                // Going up
                curr = s.removeFirst();
                result.add(curr.data);
                curr = curr.right; // Going right
            }
        }
        return result;
    }

    //Time - O(n), Space - O(h)
    public List<Integer> preorderIterative(){
        Deque<BinaryTreeNode> path = new LinkedList<>();
        path.addFirst(root);
        List<Integer> result = new ArrayList<>();
        while (!path.isEmpty()) {
            BinaryTreeNode curr = path.removeFirst();
            if (curr != null) {
                result.add(curr.data);
                path.addFirst(curr.right);
                path.addFirst(curr.left);
            }
        }
        return result;
    }

    public List<BinaryTreeNode> exteriorBinaryTree(BinaryTreeNode tree) {
        List<BinaryTreeNode> exterior = new LinkedList<>();
        if (tree != null) {
            exterior.add(tree);
            exterior.addAll(leftBoundaryAndLeaves(tree.left, true));
            exterior.addAll(rightBoundaryAndLeaves(tree.right , true));
        }
        return exterior;
    }

    // Computes the nodes from the root to the leftmost leaf followed by all the
    // leaves in subtreeRoot.
    private List<BinaryTreeNode> leftBoundaryAndLeaves(BinaryTreeNode subtreeRoot, boolean isBoundary) {
        List<BinaryTreeNode> result = new LinkedList<>();
        if (subtreeRoot != null) {
            if (isBoundary || isLeaf(subtreeRoot)){
                result.add(subtreeRoot);
            }
            result.addAll(leftBoundaryAndLeaves(subtreeRoot.left , isBoundary));
            result.addAll(leftBoundaryAndLeaves(
                    subtreeRoot.right , isBoundary && subtreeRoot.left == null));
        }
        return result ;
    }

    // Computes the leaves in left-to-right order followed by the rightmost leaf
    // to the root path in subtreeRoot.
    private List<BinaryTreeNode> rightBoundaryAndLeaves(BinaryTreeNode subtreeRoot, boolean isBoundary) {
        List<BinaryTreeNode> result = new LinkedList<>();
        if (subtreeRoot != null) {
            result.addAll(rightBoundaryAndLeaves(
                    subtreeRoot.left, isBoundary && subtreeRoot.right == null));
            result.addAll(rightBoundaryAndLeaves(subtreeRoot.right, isBoundary));
            if (isBoundary || isLeaf(subtreeRoot)) {
                result.add(subtreeRoot);
            }
        }
        return result;
    }

    private boolean isLeaf (BinaryTreeNode node) {
        return node.left == null && node.right == null;
    }

    public int[] toBSTArray() {
        int size = 20;
        int [] BSTarray = new int [size];
        makeArray(root, 0, BSTarray);
        return BSTarray;
    }

    //helper method called by toBSTArray
    public void makeArray(BinaryTreeNode node, int i, int [] BSTarray ) {
        if (node != null) {
            BSTarray[i] = node.data;
            makeArray(node.left, 2*i+1, BSTarray);
            makeArray(node.right, 2*i+2, BSTarray);
        }
    }

    // Builds a BST from preorderSequence.subList(start, end).
    // Worst case is left-skewed tree
    public BinaryTreeNode rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        return rebuildBSTFromPreorderHelper(preorderSequence, 0, preorderSequence.size());
    }

    private BinaryTreeNode rebuildBSTFromPreorderHelper(List<Integer> preorderSequence, int start, int end) {
        if (start >= end) {
            return null;
        }
        int i = start + 1;
        while (i < end && preorderSequence.get(i) < preorderSequence.get(start)) {
            ++i;
        }

        int data = preorderSequence.get(start);
        BinaryTreeNode left = rebuildBSTFromPreorderHelper(preorderSequence, start + 1, i);
        BinaryTreeNode right = rebuildBSTFromPreorderHelper(preorderSequence, i, end);
        return new BinaryTreeNode(data, left, right);
    }

    // Global variable , tracks current subtree.
    private static Integer rootIdx;

    public BinaryTreeNode rebuildBSTFromPreorder2(List<Integer> preorderSequence) {
        rootIdx = 0;
        return rebuildBSFromPreorderOnValueRange(preorderSequence, Integer.MIN_VALUE , Integer.MAX_VALUE);
    }

    // Builds a BST on the subtree rooted at rootldx from preorderSequence on keys
    // in (lowerBound, upperBound).
    private BinaryTreeNode rebuildBSFromPreorderOnValueRange(List<Integer> preorderSequence, Integer lowerBound, Integer upperBound) {
        if (rootIdx == preorderSequence.size()) {
            return null;
        }
        Integer root = preorderSequence.get(rootIdx);
        if (root < lowerBound || root > upperBound) {
            return null;
        }
        ++rootIdx;
        // Note that rebuildBSFromPreorderOnValueRange updates rootIdx. So the order of following two calls are critical.
        BinaryTreeNode leftSubtree = rebuildBSFromPreorderOnValueRange(preorderSequence, lowerBound, root);
        BinaryTreeNode rightSubtree = rebuildBSFromPreorderOnValueRange(preorderSequence, root, upperBound);
        return new BinaryTreeNode(root, leftSubtree, rightSubtree);
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
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.inorder();
        System.out.println("\n" + Arrays.toString(tree.toBSTArray()));
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
        System.out.println("isBalanced : " + tree.isBalanced());
        System.out.println("MinDepth : " + tree.minDepth());
    }
}