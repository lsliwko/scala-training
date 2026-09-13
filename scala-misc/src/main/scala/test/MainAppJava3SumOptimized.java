package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainAppJava3SumOptimized {

//  Problem: 3Sum
//
//  Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
//
//  i != j, i != k, j != k, and
//  nums[i] + nums[j] + nums[k] == 0
//
//  The result must not contain duplicate triplets (as sets of values, not indices)

    public static void main(String[] args) {
        MainAppJava3SumOptimized mainApp = new MainAppJava3SumOptimized();
        //var result = mainApp.threeSum(new int[] {-1,0,1,2,-1,-4});

        var result = mainApp.threeSum(new int[] {-4,-1,-1,0,1,2});
        
        System.out.println("Result: " + result);
    }
    
    public Set<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        var results = new HashSet<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break; // all values on right are positive, so will total >0
            
            var left = i + 1;
            var right = nums.length - 1;

            while (left < right) {

                var sum = nums[left] + nums[i] + nums[right];
                System.out.println("Trying: " + nums[left] + "," + nums[i] + "," + nums[right] + "   sum=" + sum);

                if (sum == 0) {
                    //triplet found

                    var result = Arrays.asList(nums[left], nums[i], nums[right]);
                    results.add(result);
                    System.out.println("Found: " + result);

                    left++;
                    right--;
                } else if (sum > 0) {
                    //too much, decrease
                    right--;
                } else {
                    //too little, increase
                    left++;
                }
            }
        }

        return results;
    }

}
