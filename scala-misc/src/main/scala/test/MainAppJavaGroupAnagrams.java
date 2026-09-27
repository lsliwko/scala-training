package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainAppJavaGroupAnagrams {

//Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
//
//An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
//
//Example 1:
//
//Input: strs = ["act","pots","tops","cat","stop","hat"]
//
//Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
//Example 2:
//
//Input: strs = ["x"]
//
//Output: [["x"]]
//Example 3:
//
//Input: strs = [""]
//
//Output: [[""]]

    public static void main(String[] args) {
        MainAppJavaGroupAnagrams mainApp = new MainAppJavaGroupAnagrams();

        {
            var result = mainApp.groupAnagrams(new String[] {"act","pots","tops","cat","stop","hat"});
            System.out.println("RESULT: " + result);
        }
        {
            var result = mainApp.groupAnagrams(new String[] {"x"});
            System.out.println("RESULT: " + result);
        }
        {
            var result = mainApp.groupAnagrams(new String[] {""});
            System.out.println("RESULT: " + result);
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        
        var result = new HashMap<String, List<String>>();
        for (String str : strs) {
            var keyArray = str.toCharArray();
            Arrays.sort(keyArray);
            var key = new String(keyArray);
            
            var list = result.computeIfAbsent(
                    key,
                    k -> new ArrayList<>()
            );
            list.add(str);
        }

        return result.values().stream().toList();
    }
    
}
