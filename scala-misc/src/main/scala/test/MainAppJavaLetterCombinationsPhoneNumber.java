package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainAppJavaLetterCombinationsPhoneNumber {

//3Sum Closest
//Medium
//Topics
//premium lock icon
//Companies
//You are given an integer array nums of length n and an integer target.
//
//Find three integers at distinct indices in nums such that the sum is closest to target.
//
//Return the sum of the three integers.
//
//You may assume that each input would have exactly one solution.
    
    public static void main(String[] args) {
        MainAppJavaLetterCombinationsPhoneNumber mainApp = new MainAppJavaLetterCombinationsPhoneNumber();
        {
            var digits = "23";
            var result = mainApp.letterCombinations(digits);
            System.out.println(result);
        }
        {
            var digits = "2";
            var result = mainApp.letterCombinations(digits);
            System.out.println(result);
        }
        {
            var digits = "";
            var result = mainApp.letterCombinations(digits);
            System.out.println(result);
        }
    }

    Map<Character, Character[]> map = Map.of(
            '2', new Character[] {'a','b','c'},
            '3', new Character[] {'d','e','f'},
            '4', new Character[] {'g','h','i'},
            '5', new Character[] {'j','k','l'},
            '6', new Character[] {'m','n','o'},
            '7', new Character[] {'p','q','r','s'},
            '8', new Character[] {'t','u','v'},
            '9', new Character[] {'w','x','y','z'}
    );

    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<>();
        result.add("");
        for (var c : digits.toCharArray()) {
            result = letterCombinations(c, result);
        }
        
        return result;
    }
    
    public List<String> letterCombinations(char c, List<String> combinations) {
        var newCombinations = new ArrayList<String>();
        var chars = map.get(c);
        for (var ch : chars) {
            for (var combination : combinations) {
                newCombinations.add(combination + ch);
            }
        }
        
        return newCombinations;
    }
    
}
