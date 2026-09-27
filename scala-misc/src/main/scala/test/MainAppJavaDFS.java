package test;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MainAppJavaDFS {

//Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
//
//You must implement a solution with a linear runtime complexity and use only constant extra space.
//
// 
//
//Example 1:
//
//Input: nums = [2,2,3,2]
//Output: 3
//Example 2:
//
//Input: nums = [0,1,0,1,0,1,99]
//Output: 99

    public static void main(String[] args) {
        MainAppJavaDFS mainApp = new MainAppJavaDFS();

        {
            var root = TreeNode.buildTree(new Integer[] {1, 2, 3, 4, 5, 6, 7});
            var result = mainApp.traverse(root, false);
            System.out.println("RESULT: " + result);
        }
//        {
//            var root = TreeNode.buildTree(new Integer[] {10, 5, 15, null, null, 6, 20});
//            var result = mainApp.checkBST(root);
//            System.out.println("RESULT: " + result);
//        }
    }

    public boolean traverse(TreeNode root, boolean dfsOrBfsFlag) {
        if (root == null) return true;
        
        List<Integer> values = new ArrayList<>();
        var deque = new ArrayDeque<TreeNode>();

        deque.push(root);
        while (!deque.isEmpty()) {
            var node = deque.pop();

            values.add(node.val);
            System.out.println("Visited: " + node.val);

            if (dfsOrBfsFlag) {
                // push = addFirst (head) + pop = removeFirst (head) => LIFO => DFS
                if (node.left != null) deque.push(node.left);
                if (node.right != null) deque.push(node.right);
            } else {
                // offer = addLast (tail) + pop = removeFirst (head) => FIFO => BFS
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }
        }
        
        System.out.println("Values: " + values);
        
        return false;
    }
    
}
