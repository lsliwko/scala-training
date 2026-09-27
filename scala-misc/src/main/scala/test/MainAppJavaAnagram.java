package test;

import java.util.HashMap;
import java.util.Map;

public class MainAppJavaAnagram {

//Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.

    public static void main(String[] args) {
        MainAppJavaAnagram mainApp = new MainAppJavaAnagram();

//        {
//            var root = TreeNode.buildTree(new Integer[] {1, 2, 3, 4, 5, 6, 7});
//            var result = mainApp.hasDuplicate(root, false);
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var root = TreeNode.buildTree(new Integer[] {10, 5, 15, null, null, 6, 20});
//            var result = mainApp.checkBST(root);
//            System.out.println("RESULT: " + result);
//        }
    }
    
    private Map<Character,Integer> getMap(String input) {
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        var sMap = getMap(s);
        var tMap = getMap(t);
        
        return sMap.equals(tMap);
    }
    
}
