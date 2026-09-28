package test;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MainAppJavaLongestConsecutive {

//Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be formed.
//
//A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element. The elements do not have to be consecutive in the original array.
//
//You must write an algorithm that runs in O(n) time.
//
//Example 1:
//
//Input: nums = [2,20,4,10,3,4,5]
//
//Output: 4
//Explanation: The longest consecutive sequence is [2, 3, 4, 5].
//
//Example 2:
//
//Input: nums = [0,3,2,5,4,6,1,1]
//
//Output: 7

    public static void main(String[] args) {
        MainAppJavaLongestConsecutive mainApp = new MainAppJavaLongestConsecutive();

//        {
//            var nums = new int[] {2,20,4,10,3,4,5};
//            var result = mainApp.longestConsecutive(nums);
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var nums = new int[] {0,3,2,5,4,6,1,1};
//            var result = mainApp.longestConsecutive(nums);
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var nums = new int[] {};
//            var result = mainApp.longestConsecutive(nums);
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var nums = new int[] {1};
//            var result = mainApp.longestConsecutive(nums);
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var nums = new int[] {1,1};
//            var result = mainApp.longestConsecutive(nums);
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var nums = new int[] {12,1};
//            var result = mainApp.longestConsecutive(nums);
//            System.out.println("RESULT: " + result);
//        }
        {
            var nums = new int[] {9,1,4,7,3,-1,0,5,8,-1,6};
            var result = mainApp.longestConsecutive(nums);
            System.out.println("RESULT: " + result);
        }

    }


    public int longestConsecutive(int[] nums) {
        
        var set = new PriorityQueue<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        var result = new ArrayList<Integer>();
        var resultTmp = new ArrayList<Integer>();
        for (var num : set) {
            if (
                    (resultTmp.isEmpty()) ||
                    (num == resultTmp.getLast() + 1)
            ) {
                resultTmp.add(num);
            } else {
                if (resultTmp.size() > result.size()) {
                    result = resultTmp;
                }
                resultTmp = new ArrayList<>();
                resultTmp.add(num);
            }
        }

        if (resultTmp.size() > result.size()) {
            result = resultTmp;
        }
        
        System.out.println(result);
        
        return result.size();
    }
    
}
