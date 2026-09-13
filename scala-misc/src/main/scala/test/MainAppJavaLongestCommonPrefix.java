package test;

import java.util.Arrays;

public class MainAppJavaLongestCommonPrefix {

//Write a function to find the longest common prefix string amongst an array of strings.
//
//If there is no common prefix, return an empty string "".
//
// 
//
//Example 1:
//
//Input: strs = ["flower","flow","flight"]
//Output: "fl"
//Example 2:
//
//Input: strs = ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.
    
    public static void main(String[] args) {
        MainAppJavaLongestCommonPrefix mainApp = new MainAppJavaLongestCommonPrefix();
//        {
//            var nums = new int[] {2,7,11,15};
//            var result = mainApp.twoSum(nums, 9);
//            System.out.println(Arrays.toString(nums) + ": " + Arrays.toString(result));
//        }

        {
            var strs = new String[] {"flower","flow","flight"};
            var result = mainApp.longestCommonPrefix(strs);
            System.out.println(Arrays.toString(strs) + ": " + result);
        }
        {
            var strs = new String[] {"dog","racecar","car"};
            var result = mainApp.longestCommonPrefix(strs);
            System.out.println(Arrays.toString(strs) + ": " + result);
        }
        {
            var strs = new String[] {"aa", ""};
            var result = mainApp.longestCommonPrefix(strs);
            System.out.println(Arrays.toString(strs) + ": " + result);
        }
        {
            var strs = new String[] {"a"};
            var result = mainApp.longestCommonPrefix(strs);
            System.out.println(Arrays.toString(strs) + ": " + result);
        }
        {
            var strs = new String[] {"flower","flower","flower","flower"};
            var result = mainApp.longestCommonPrefix(strs);
            System.out.println(Arrays.toString(strs) + ": " + result);
        }
    }
    
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        if (strs[0].isEmpty()) return "";
        int i = 0;
        while (true) {
            for (String str : strs) {
                if (i == str.length()) return str.substring(0, i);
                
                var c = strs[0].charAt(i);
                if (str.charAt(i) != c) return str.substring(0, i);
            }
            i++;
        }
    }
    
}
