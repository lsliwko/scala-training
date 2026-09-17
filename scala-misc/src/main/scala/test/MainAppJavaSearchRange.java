package test;

import java.util.Arrays;

public class MainAppJavaSearchRange {

//Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
//
//If target is not found in the array, return [-1, -1].
//
//You must write an algorithm with O(log n) runtime complexity.
//
// 
//
//Example 1:
//
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]
//Example 2:
//
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]
//Example 3:
//
//Input: nums = [], target = 0
//Output: [-1,-1]
    
    
    public static void main(String[] args) {
        MainAppJavaSearchRange mainApp = new MainAppJavaSearchRange();
        {
            var nums = new int[] {5,7,7,8,8,10};
            var result = mainApp.searchRange(nums, 8);
            System.out.println("RESULT: " + Arrays.toString(nums) + ": " + Arrays.toString(result));
        }
        {
            var nums = new int[] {5,7,7,8,8,10};
            var result = mainApp.searchRange(nums, 6);
            System.out.println("RESULT: " + Arrays.toString(nums) + ": " + Arrays.toString(result));
        }
        {
            var nums = new int[] {};
            var result = mainApp.searchRange(nums, 0);
            System.out.println("RESULT: " + Arrays.toString(nums) + ": " + Arrays.toString(result));
        }
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[] {-1,-1};

        int minRight = -1, maxLeft = -1;
        {
            //search left
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                var mid = left + (right - left) / 2;

                var midVal = nums[mid];
                if (midVal < target) {
                    left = mid + 1;
                } else {
                    if (midVal == target) minRight = mid;
                    right = mid - 1;
                }
            }
        }

        {
            //search right
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                var mid = left + (right - left) / 2;

                var midVal = nums[mid];
                if (midVal > target) {
                    right = mid - 1;
                } else {
                    if (midVal == target) maxLeft = mid;
                    left = mid + 1;
                }
            }
        }
        return new int[] {minRight,maxLeft};
    }
    
}
