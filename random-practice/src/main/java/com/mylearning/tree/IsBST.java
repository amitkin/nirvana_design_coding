package com.mylearning.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
    BinaryTreeNode prev = null;
    public boolean isValidBST(BinaryTreeNode root) {
        if(root != null){
            if(!isValidBST(root.left)) {
                return false;
            }
            if(prev != null && prev.data >= root.data) {
                return false;
            }
            prev = root;
            return isValidBST(root.right);
        }
        return true;
    }

    public static void main(String args[]){
        BinarySearchTree bt = new BinarySearchTree();
        bt.insert(10);
        bt.insert(15);
        bt.insert(-10);
        bt.insert(17);
        bt.insert(20);
        bt.insert(0);
        /*bt.insert(2);
        bt.insert(1);
        bt.insert(3);*/

        IsBST isBST = new IsBST();
        System.out.println(isBST.isBST(bt.root));
        System.out.println(isBST.isBSTIterative(bt.root));
        System.out.println(isBST.isBinaryTreeBST(bt.root));
        System.out.println(isBST.isValidBST(bt.root));
    }
}
