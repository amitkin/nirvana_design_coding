package com.mylearning.tree;

import com.mylearning.tree.BinarySearchTree.BinaryTreeNode;

public class LargestBSTInBinaryTree {

    class MinMax {
        int size;
        int lower, upper;
        boolean isBST;

        public MinMax(){
            lower = Integer.MAX_VALUE;
            upper = Integer.MIN_VALUE;
            isBST = false;
            size = 0;
        }
    }

    public int largestBSTSubtree(BinaryTreeNode root) {
        return largestBSTSubtreeUtil(root).size;
    }

    private MinMax largestBSTSubtreeUtil(BinaryTreeNode node){
        MinMax curr = new MinMax();

        if(node == null){
            curr.isBST= true;
            return curr;
        }

        MinMax l = largestBSTSubtreeUtil(node.left);
        MinMax r = largestBSTSubtreeUtil(node.right);

        //current subtree's boundaries
        curr.lower = Math.min(node.data, l.lower);
        curr.upper = Math.max(node.data, r.upper);

        //check left and right subtrees are BST or not
        //check left's upper against current's value and right's lower against current's value
        if(l.isBST && r.isBST && l.upper<=node.data && r.lower>=node.data){
            curr.size = l.size+r.size+1;
            curr.isBST = true;
        }else{
            curr.size = Math.max(l.size, r.size);
            curr.isBST  = false;
        }
        return curr;
    }

    public static void main(String args[]){
        BinarySearchTree bt = new BinarySearchTree();
        bt.insert(10);
        bt.insert(-10);
        bt.insert(15);
        bt.insert(17);
        bt.insert(20);
        bt.insert(0);

        LargestBSTInBinaryTree largestBSTInBinaryTree = new LargestBSTInBinaryTree();
        System.out.println(largestBSTInBinaryTree.largestBSTSubtree(bt.root));
    }
}
