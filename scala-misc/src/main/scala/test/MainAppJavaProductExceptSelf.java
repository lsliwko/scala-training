package test;

import java.util.Arrays;

public class MainAppJavaProductExceptSelf {

//Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
//
//Each product is guaranteed to fit in a 32-bit integer.
//
//Follow-up: Could you solve it in 
//O
//(
//n
//)
//O(n) time without using the division operation?
//
//Example 1:
//
//Input: nums = [1,2,4,6]
//
//Output: [48,24,12,8]
//Example 2:
//
//Input: nums = [-1,0,1,2,3]
//
//Output: [0,-6,0,0,0]

    public static void main(String[] args) {
        MainAppJavaProductExceptSelf mainApp = new MainAppJavaProductExceptSelf();

        {
            var nums = new int[] {1,2,4,6};
            var result = mainApp.productExceptSelf(nums);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        {
            var nums = new int[] {-1,0,1,2,3};
            var result = mainApp.productExceptSelf(nums);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        {
            var nums = new int[] {1,0,-1};
            var result = mainApp.productExceptSelf(nums);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        
    }
    
    //             1              2       4        6
    //from left:   1              1   1x2=2  1x2x4=8
    //from right:  6x4x2=48  6x4=24       6        1
    //result:      48       1x24=24  2x6=12.       8
    

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        if (nums.length == 1) return new int[] {0};
        
        var resultFromLeft  = new int[nums.length];
        resultFromLeft[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            resultFromLeft[i] = nums[i-1] * resultFromLeft[i-1];
        }

        var resultFromRight = new int[nums.length];
        resultFromRight[nums.length-1] = 1;
        for (int i = nums.length-2; i >= 0; i--) {
            resultFromRight[i] = nums[i+1] * resultFromRight[i+1];
        }
        
        var result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = resultFromLeft[i] * resultFromRight[i];
        }
        
        return result;
    }
    
}
