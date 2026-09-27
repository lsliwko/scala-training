package test;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.right = right;
        this.left = left;
    }

    static public TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();

            // left child — guaranteed in bounds by the while condition, no check needed
            Integer leftVal = values[i++];
            if (leftVal != null) {
                current.left = new TreeNode(leftVal);
                queue.offer(current.left);
            }

            // right child — i may now be out of bounds after the increment above, so check
            if (i < values.length) {
                Integer rightVal = values[i++];
                if (rightVal != null) {
                    current.right = new TreeNode(rightVal);
                    queue.offer(current.right);
                }
            }
        }

        return root;
    }
}
