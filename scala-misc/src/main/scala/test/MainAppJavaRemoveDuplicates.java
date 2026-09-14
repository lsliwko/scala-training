package test;

import java.util.Arrays;

public class MainAppJavaRemoveDuplicates {

//Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
//
//Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.
//
//The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.
    
    public static void main(String[] args) {
        MainAppJavaRemoveDuplicates mainApp = new MainAppJavaRemoveDuplicates();
//        {
//            var nums = new int[] {2,7,11,15};
//            var result = mainApp.twoSum(nums, 9);
//            System.out.println(Arrays.toString(nums) + ": " + Arrays.toString(result));
//        }

        {
            var nums = new int[] {1,1,2};
            var result = mainApp.removeDuplicates(nums);
            System.out.println(Arrays.toString(nums) + ": " + result);
        }
        {
            var nums = new int[] {2};
            var result = mainApp.removeDuplicates(nums);
            System.out.println(Arrays.toString(nums) + ": " + result);
        }
        {
            var nums = new int[] {2,2};
            var result = mainApp.removeDuplicates(nums);
            System.out.println(Arrays.toString(nums) + ": " + result);
        }
        {
            var nums = new int[] {2,3,3};
            var result = mainApp.removeDuplicates(nums);
            System.out.println(Arrays.toString(nums) + ": " + result);
        }
        {
            var nums = new int[] {};
            var result = mainApp.removeDuplicates(nums);
            System.out.println(Arrays.toString(nums) + ": " + result);
        }
        {
            var nums = new int[] {0,0,1,1,1,2,2,3,3,4};
            var result = mainApp.removeDuplicates(nums);
            System.out.println(Arrays.toString(nums) + ": " + result);
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        
        int writeIndex = 0;
        int lastInt = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i==0) lastInt = nums[i]; 
            else if (nums[i] != lastInt) {
                nums[++writeIndex] = nums[i];
            }
            lastInt = nums[i];
        }

        return writeIndex+1;
    }
    
}
