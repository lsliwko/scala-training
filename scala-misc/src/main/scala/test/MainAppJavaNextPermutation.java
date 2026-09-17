package test;

import java.util.Arrays;

public class MainAppJavaNextPermutation {

//The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
//
//For example, the next permutation of arr = [1,2,3] is [1,3,2].
//Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
//While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
//Given an array of integers nums, find the next permutation of nums.
//
//The replacement must be in place and use only constant extra memory.
    
//Example 1:
//Input: nums = [1,2,3]
//Output: [1,3,2]
    
//Example 2:
//Input: nums = [3,2,1]
//Output: [1,2,3]
    
//Example 3:
//Input: nums = [1,1,5]
//Output: [1,5,1]
    
    
    public static void main(String[] args) {
        MainAppJavaNextPermutation mainApp = new MainAppJavaNextPermutation();
        {
            var nums = new int[] {5,3,1};
            mainApp.nextPermutation(nums);
            System.out.println("RESULT: " + Arrays.toString(nums));
        }
        {
            var nums = new int[] {3,2,1};
            mainApp.nextPermutation(nums);
            System.out.println("RESULT: " + Arrays.toString(nums));
        }
        {
            var nums = new int[] {3,2,1};
            mainApp.nextPermutation(nums);
            System.out.println("RESULT: " + Arrays.toString(nums));
        }
        {
            var nums = new int[] {1,2,3};
            mainApp.nextPermutation(nums);
            System.out.println("RESULT: " + Arrays.toString(nums));
        }
        {
            var nums = new int[] {1,1,5};
            mainApp.nextPermutation(nums);
            System.out.println("RESULT: " + Arrays.toString(nums));
        }
        {
            var nums = new int[] {4,2,3,5,4,1};
            mainApp.nextPermutation(nums);
            System.out.println("RESULT: " + Arrays.toString(nums));
        }
    }

    public void nextPermutation(int[] nums) {
        System.out.println("START: " + Arrays.toString(nums));

        if (nums.length == 1) return;
        
        int i = nums.length-1;
        //go left
        while (i > 0) {
            if (nums[i-1] < nums[i]) break;
            i--;
        }
        //no possible next permutation
        if (i == 0) {
            System.out.println(Arrays.toString(nums) + " next not found");
            Arrays.sort(nums);
            return;
        }

        int toSwap = nums[i-1];
        System.out.println(Arrays.toString(nums) + " pivot: " + nums[i] + " to-swap: " + toSwap);
        
        
        Arrays.sort(nums, i, nums.length);
        System.out.println(Arrays.toString(nums) + " sorted");
        
        //find next larger
        for (int j = i; j < nums.length; j++) {
            if (nums[j] > toSwap) {
                var temp = nums[j];
                nums[j] = toSwap;
                nums[i-1] = temp;
                break;
            };
        }
    }
    
}
