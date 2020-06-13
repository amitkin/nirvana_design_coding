package com.mylearning.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mylearning.tree.BinarySearchTree.BinaryTreeNode;

//Use preorder approach
public class SerializeDeserialize {

    // Encodes a tree to a single string.
    public String serialize(BinaryTreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(BinaryTreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X").append(",");
        } else {
            sb.append(node.data).append(",");
            serializeHelper(node.left, sb);
            serializeHelper(node.right,sb);
        }
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(",")));
        return deserializeHelper(nodes);
    }

    private BinaryTreeNode deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals("X")) return null;
        else {
            BinaryTreeNode node = new BinaryTreeNode(Integer.valueOf(val));
            node.left = deserializeHelper(nodes);
            node.right = deserializeHelper(nodes);
            return node;
        }
    }

    //=================================Facebook Question================================================================

    public static  List<Integer> serializeF(BinaryTreeNode root){
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private static void preOrder(BinaryTreeNode root, List<Integer> result) {
        if(root == null){
            result.add(-1);
            return;
        }
        result.add(root.data);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    public static BinaryTreeNode deSerializeF(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        //Imp to note that Queue is not created here but iterator is used.
        BinaryTreeNode root = deSerializeFHelper(iterator);
        return root;

    }

    private static BinaryTreeNode deSerializeFHelper(Iterator<Integer> iterator) {
        Integer next = iterator.next();
        if(next == null){
            return  null;
        }

        if(next == -1){
            return  null;
        }

        BinaryTreeNode root = new BinaryTreeNode(next);
        root.left = deSerializeFHelper(iterator);
        root.right = deSerializeFHelper(iterator);
        return  root;
    }

    //==================================================================================================================

    public List<Integer> serializeToArrayList(BinaryTreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new LinkedList<>();
        serializeToArrayListHelper(root, res);
        return res;
    }

    private void serializeToArrayListHelper(BinaryTreeNode node, List<Integer> res) {
        if (node == null) {
            res.add(-1);
        } else {
            res.add(node.data);
            serializeToArrayListHelper(node.left, res);
            serializeToArrayListHelper(node.right,res);
        }
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserializeFromArrayList(List<Integer> data) {
        if (data.size() == 0) return null;
        List<Integer> values = new ArrayList<>(data);
        Queue<Integer> q = new LinkedList<>(values);
        return deserializeFromArrayHelper(q);
    }

    private BinaryTreeNode deserializeFromArrayHelper(Queue<Integer> nodes) {
        Integer val = nodes.poll();
        if (val == null || val == -1) return null;
        else {
            BinaryTreeNode node = new BinaryTreeNode(val);
            node.left = deserializeFromArrayHelper(nodes);
            node.right = deserializeFromArrayHelper(nodes);
            return node;
        }
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

        List<Integer> savedTreeFList = SerializeDeserialize.serializeF(tree.root);
        BinaryTreeNode deserializedFTree = SerializeDeserialize.deSerializeF(savedTreeFList);

        ArrayList<Integer> deserializedFArrayTree = new ArrayList<>();
        tree.inorderArray(deserializedFTree, deserializedFArrayTree);
        System.out.println("DeserializedF Tree : " + deserializedFArrayTree.toString());

        SerializeDeserialize serializeDeserialize = new SerializeDeserialize();

        String savedTree = serializeDeserialize.serialize(tree.root);
        BinaryTreeNode root = serializeDeserialize.deserialize(savedTree);

        List<Integer> deserializedTree = new ArrayList<>();
        tree.inorderArray(root, deserializedTree);

        System.out.println("Deserialized Tree : " + deserializedTree.toString());

        List<Integer> savedTreeList =  serializeDeserialize.serializeToArrayList(tree.root);
        root = serializeDeserialize.deserializeFromArrayList(savedTreeList);

        deserializedTree = new ArrayList<>();
        tree.inorderArray(root, deserializedTree);

        System.out.println("Deserialized Tree using Array : " + deserializedTree.toString());
    }
}
