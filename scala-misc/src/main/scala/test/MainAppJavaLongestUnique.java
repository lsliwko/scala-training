package test;

import java.util.HashMap;

public class MainAppJavaLongestUnique {

//    Given two strings s and t, return the minimum window substring of s such that every character
//    in t (including duplicates) is included in the window.
//    If there is no such substring, return the empty string "".
//
//    Example:
//    Input: s = "ADOBECODEBANC", t = "ABC"
//    Output: "BANC"

    public static void main(String[] args) {
        MainAppJavaLongestUnique mainApp = new MainAppJavaLongestUnique();
        var result = mainApp.longestSubstring("abba");
        
        System.out.println("Result: " + result);
    }
    
    public String longestSubstring(String s) {
        if (s.isEmpty()) return "";

        var lastSeen = new HashMap<Character, Integer>();
        int left = 0;
        String noDupsMax = "";

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            var lastSeenC = lastSeen.getOrDefault(c, 0);
            if (lastSeenC >= left) {
                left = lastSeenC + 1;
            }

            var temp = s.substring(left, right + 1);
            System.out.println("No dups:" + temp);

            if (right - left + 1 > noDupsMax.length()) {
                noDupsMax = temp;
            }

            lastSeen.put(c, right);
        }
        
        return noDupsMax;
    }

}
