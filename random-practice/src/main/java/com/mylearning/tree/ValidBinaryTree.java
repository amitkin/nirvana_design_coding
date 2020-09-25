package com.mylearning.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mylearning.tree.BinarySearchTree.BinaryTreeNode;

//https://leetcode.com/discuss/interview-question/347374/ - Find out if all these nodes belong to the same valid binary tree.
//Google and Facebook
//1. There can be one and only one root
//2. All nodes should have only one indegree, except the root node, which has zero indegree
//3. Check if there any node missed from the given list
public class ValidBinaryTree {
    public boolean isBinaryTree(List<BinaryTreeNode> nodes) {
        Map<BinaryTreeNode, Integer> inDegree = new HashMap<>();
        for (BinaryTreeNode node : nodes) {
            if (node.left != null) {
                inDegree.put(node.left, inDegree.getOrDefault(node.left, 0) + 1);
            }
            if (node.right != null) {
                inDegree.put(node.right, inDegree.getOrDefault(node.right, 0) + 1);
            }
        }
        BinaryTreeNode root = null;
        for (BinaryTreeNode node : nodes) {
            if (!inDegree.containsKey(node)) {
                if (root == null) {
                    root = node;
                } else {
                    return false; //There can be one and only one root
                }
            } else if (inDegree.get(node) != 1) { //All nodes should have only one indegree, except the root node, which has zero indegree
                return false;
            }
        }
        return root != null && nodes.size() == inDegree.keySet().size() + 1; //Check if there any node missed from the given list
    }

    public static void main(String[] args) {
        ValidBinaryTree validBinaryTree = new ValidBinaryTree();
        System.out.println(validBinaryTree.isBinaryTree(Arrays.asList()));
    }
}
