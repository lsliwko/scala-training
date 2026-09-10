package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainAppJava {

//    Given two strings s and t, return the minimum window substring of s such that every character
//    in t (including duplicates) is included in the window.
//    If there is no such substring, return the empty string "".
//
//    Example:
//    Input: s = "ADOBECODEBANC", t = "ABC"
//    Output: "BANC"

    public static void main(String[] args) {
        MainAppJava mainApp = new MainAppJava();
        var result = mainApp.anagrams("aecebacceecae", "cea");
        
        System.out.println("Result: " + result);
    }
    
    public List<Integer> anagrams(String s, String p) {
        var resultIndices = new ArrayList<Integer>();
        if (s.length() < p.length()) return resultIndices;
        

        var pStringCounts = new HashMap<Character, Integer>();
        for (int i = 0; i < p.length(); i++) {
            pStringCounts.merge(p.charAt(i), 1, Integer::sum);
        }
        System.out.println("Target: " + pStringCounts);

        var tmpStringCounts = new HashMap<Character, Integer>();
        for (int right=0; right < s.length(); right++) {
            
            //add to count
            char cRight = s.charAt(right);
            tmpStringCounts.put(cRight, tmpStringCounts.getOrDefault(cRight, 0) + 1);


            int left = right - p.length();
            if (left >= 0) {
                //remove from count
                char cLeft = s.charAt(left);
                var count = tmpStringCounts.get(cLeft);
                if (count == 1) {
                    tmpStringCounts.remove(cLeft);
                } else {
                    tmpStringCounts.put(cLeft, tmpStringCounts.get(cLeft) - 1);
                }
            }
            
            var acceptFlag = (tmpStringCounts.equals(pStringCounts));

            if (acceptFlag) {
                resultIndices.add(left + 1);
            }
            
            //debug only
            {
                var temp = s.substring(Math.max(0, left+1), right + 1);
                System.out.println(temp + " " + tmpStringCounts + " " + (acceptFlag ? "OK" : ""));
            }
        }
        
        return resultIndices;
    }

}
