package com.mylearning.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import com.mylearning.epi.tree.BinaryTree;
import com.mylearning.tree.BinarySearchTree.BinaryTreeNode;

public class IsBST {

    public boolean isBST(BinaryTreeNode root){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //DFS - Recursive
    private boolean isBST(BinaryTreeNode root, int min, int max){
        if(root == null){
            return true;
        }
        if(root.data <= min || root.data > max){
            return false;
        }
        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }


    //DFS Approach - Uses inorder traversal property
    public boolean isBSTIterative(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode node = root;
        int prev = Integer.MIN_VALUE;
        int current;
        while ( true ) {
            if (node != null) {
                stack.addFirst(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pollFirst();
                current = node.data;
                if (current < prev) {
                    return false;
                }
                prev = current;
                node = node.right;
            }
        }
        return true;
    }

    public static class QueueEntry {
        public BinaryTreeNode node;
        public Integer lowerBound , upperBound;
        public QueueEntry(BinaryTreeNode node, Integer lowerBound, Integer upperBound) {
            this.node = node;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    //BFS approach
    public boolean isBinaryTreeBST(BinaryTreeNode tree) {
        Queue<QueueEntry> BFSQueue = new LinkedList<>();
        BFSQueue.add(new QueueEntry(tree , Integer.MIN_VALUE , Integer.MAX_VALUE));
        QueueEntry headEntry = BFSQueue.poll();
        while (headEntry!= null) {
            if (headEntry.node != null) {
                if (headEntry.node.data < headEntry.lowerBound || headEntry.node.data > headEntry.upperBound) {
                    return false;
                }
                BFSQueue.add(new QueueEntry(headEntry.node.left, headEntry.lowerBound, headEntry.node.data));
                BFSQueue.add(new QueueEntry(headEntry.node.right, headEntry.node.data, headEntry.upperBound));
            }
            headEntry = BFSQueue.poll();
        }
        return true;
    }

    //Inorder approach
    /*BinaryTreeNode prev = null;
    public boolean isValidBST(BinaryTreeNode node) {
        if(node != null){
            if(!isValidBST(node.left)) {
                return false;
            }
            if(prev != null && prev.data >= node.data) {
                return false;
            }
            prev = node;
            if(!isValidBST(node.right)) {
                return false;
            }
        }
        return true;
    }*/



    public boolean isValidBST (BinaryTreeNode root){
        //return isValidBSTHelper(root, null);

        ResultType r = isValidBSTHelper(root);
        return r.is_bst;
    }

    class ResultType {
        boolean is_bst;
        int maxValue, minValue;

        ResultType(boolean is_bst, int maxValue, int minValue) {
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    //Bottom-up approach, very tough
    private ResultType isValidBSTHelper(BinaryTreeNode root) {
        if (root == null) {
            //Important to note this - reverse order
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ResultType left = isValidBSTHelper(root.left);
        ResultType right = isValidBSTHelper(root.right);

        //Important to note below two if conditions
        if (!left.is_bst || !right.is_bst) {
            // if is_bst is false then minValue and maxValue are useless
            return new ResultType(false, 0, 0);
        }

        if (root.left != null && left.maxValue >= root.data ||
                root.right != null && right.minValue <= root.data) {
            return new ResultType(false, 0, 0);
        }

        return new ResultType(true, Math.max(root.data, right.maxValue), Math.min(root.data, left.minValue));
    }

    public static void main(String args[]){
        BinarySearchTree bt = new BinarySearchTree();
        /*bt.add(10);
        bt.add(15);
        bt.add(-10);
        bt.add(17);
        bt.add(20);
        bt.add(0);*/
        bt.add(2);
        bt.add(1);
        bt.add(3);
        /*bt.root = new BinaryTreeNode(5);
        bt.root.left = new BinaryTreeNode(1);
        bt.root.right = new BinaryTreeNode(4);
        bt.root.right.left = new BinaryTreeNode(3);
        bt.root.right.right = new BinaryTreeNode(6);*/
        bt.inorder();

        IsBST isBST = new IsBST();
        System.out.println(isBST.isBST(bt.root));
        System.out.println(isBST.isBSTIterative(bt.root));
        System.out.println(isBST.isBinaryTreeBST(bt.root));

        BinaryTreeNode root = new BinaryTreeNode(1);
        //root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(1);
        //root.right.left = new BinaryTreeNode(3);
        //root.right.right = new BinaryTreeNode(6);
        System.out.println(isBST.isValidBST(bt.root));
    }
}
