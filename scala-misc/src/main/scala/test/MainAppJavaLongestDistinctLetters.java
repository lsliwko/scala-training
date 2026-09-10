package test;

import java.util.HashMap;

public class MainAppJavaLongestDistinctLetters {

//    Given two strings s and t, return the minimum window substring of s such that every character
//    in t (including duplicates) is included in the window.
//    If there is no such substring, return the empty string "".
//
//    Example:
//    Input: s = "ADOBECODEBANC", t = "ABC"
//    Output: "BANC"

    public static void main(String[] args) {
        MainAppJavaLongestDistinctLetters mainApp = new MainAppJavaLongestDistinctLetters();
        var result = mainApp.longestSubstring("eceba", 2);
        
        System.out.println("Result: " + result);
    }
    
    public String longestSubstring(String s, int k) {
        if (s.isEmpty()) return "";

        var resultStringCounts = new HashMap<Character, Integer>();
        String resultString = "";

        int left = 0;
        int right = 0;

        while ((right < s.length()) && (left < s.length())) {

            if (resultStringCounts.size() <= k) {
                char c = s.charAt(right);

                //add to count
                resultStringCounts.put(c, resultStringCounts.getOrDefault(c, 0) + 1);
                right++;
            } else {
                char c = s.charAt(left);

                //remove from count
                var count = resultStringCounts.get(c);
                if (count == 1) {
                    resultStringCounts.remove(c);
                } else {
                    resultStringCounts.put(c, resultStringCounts.get(c) - 1);
                }
                left++;
            }

            var acceptFlag = (resultStringCounts.size() <= k);

            //debug only
            {
                var temp = s.substring(left, right);
                System.out.println(temp + " " + resultStringCounts + " " + (acceptFlag ? "OK" : ""));
            }
            
            if (acceptFlag) {
                if (right - left > resultString.length()) {
                    resultString = s.substring(left, right);
                }
            }
       
        }
        
        return resultString;
    }

}
