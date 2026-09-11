package test;

import java.util.HashMap;
import java.util.Map;

public class MainAppJavaLongestFlipSubstring {

//    Given two strings s and t, return the minimum window substring of s such that every character
//    in t (including duplicates) is included in the window.
//    If there is no such substring, return the empty string "".
//
//    Example:
//    Input: s = "ADOBECODEBANC", t = "ABC"
//    Output: "BANC"

    public static void main(String[] args) {
        MainAppJavaLongestFlipSubstring mainApp = new MainAppJavaLongestFlipSubstring();
        var result = mainApp.longestFlipSubstring("abab", 2);
        
        System.out.println("Result: " + result);
    }
    
    public String longestFlipSubstring(String s, int k) {
        if (s.isEmpty()) return "";

        var resultStringCounts = new HashMap<Character, Integer>();
        String resultString = "";

        int left = 0;
        int right = 0;

        while ((right < s.length()) && (left < s.length())) {

            //debug only
            var temp = s.substring(left, right);
            
            //select top 
            Map.Entry<Character, Integer> topPair = null;
            for (var pair : resultStringCounts.entrySet()) {
                if ((topPair == null) || (topPair.getValue() < pair.getValue())) {
                    topPair = pair;
                }
            }

            var totalNotTopChar = 0L;
            for (var pair : resultStringCounts.entrySet()) {
                if (pair.getKey() != topPair.getKey()) totalNotTopChar = totalNotTopChar + pair.getValue();
            }

            var acceptFlag = totalNotTopChar <= k;
            
            if (acceptFlag) {
                if (right - left > resultString.length()) {
                    resultString = s.substring(left, right);
                }
            }
            
            //debug only
            {
                System.out.println(temp + " " + resultStringCounts + " k=" + k + " topPair: " + topPair + " " + (acceptFlag ? "OK" : ""));
            }

            if (acceptFlag) {
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

            // final window (right may have reached s.length() with a valid,
            // never-recorded window still sitting in resultStringCounts)
            if (right - left > resultString.length()) {
                resultString = s.substring(left, right);
            }
       
        }
        
        return resultString;
    }

}
