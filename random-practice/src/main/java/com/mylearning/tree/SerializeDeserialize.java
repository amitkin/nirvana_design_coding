package com.mylearning.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mylearning.tree.BinarySearchTree.BinaryTreeNode;

public class SerializeDeserialize {

    // Encodes a tree to a single string.
    public String serialize(BinaryTreeNode root) {
        if (root == null) return "";
        Queue<BinaryTreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            BinaryTreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.data + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public List<Integer> serializeToArray(BinaryTreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            BinaryTreeNode node = q.poll();
            if (node == null) {
                res.add(null);
                continue;
            }
            res.add(node.data);
            q.add(node.left);
            q.add(node.right);
        }
        return res;
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<BinaryTreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            BinaryTreeNode parent = q.poll();
            if (!values[i].equals("n")) {
                BinaryTreeNode left = new BinaryTreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals("n")) {
                BinaryTreeNode right = new BinaryTreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserializeFromArray(List<Integer> data) {
        if (data.size() == 0) return null;
        Queue<BinaryTreeNode> q = new LinkedList<>();
        List<Integer> values = new ArrayList<>(data);
        BinaryTreeNode root = new BinaryTreeNode(values.get(0));
        q.add(root);
        for (int i = 1; i < values.size(); i++) {
            BinaryTreeNode parent = q.poll();
            if (values.get(i) != null) {
                BinaryTreeNode left = new BinaryTreeNode(values.get(i));
                parent.left = left;
                q.add(left);
            }
            if (values.get(++i) != null) {
                BinaryTreeNode right = new BinaryTreeNode(values.get(i));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
                  50
               /     \
              30      70
             /  \    /  \
           20   40  60   80 */
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        System.out.println("Original Tree : ");
        tree.inorder();
        System.out.println("\n");
        SerializeDeserialize serializeDeserialize = new SerializeDeserialize();

        String savedTree = serializeDeserialize.serialize(tree.root);
        BinaryTreeNode root = serializeDeserialize.deserialize(savedTree);

        ArrayList<Integer> deserializedTree = new ArrayList<>();
        tree.inorderArray(root, deserializedTree);

        System.out.println("Deserialized Tree : " + deserializedTree.toString());

        List<Integer> savedTreeList =  serializeDeserialize.serializeToArray(tree.root);
        root = serializeDeserialize.deserializeFromArray(savedTreeList);

        deserializedTree = new ArrayList<>();
        tree.inorderArray(root, deserializedTree);

        System.out.println("Deserialized Tree using Array : " + deserializedTree.toString());
    }
}
