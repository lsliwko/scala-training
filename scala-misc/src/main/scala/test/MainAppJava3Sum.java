package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainAppJava3Sum {

//  Problem: 3Sum
//
//  Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
//  
//  i != j, i != k, j != k, and
//  nums[i] + nums[j] + nums[k] == 0
//
//  The result must not contain duplicate triplets (as sets of values, not indices)

    public static void main(String[] args) {
        MainAppJava3Sum mainApp = new MainAppJava3Sum();
        var result = mainApp.threeSum(new int[] {-1,0,1,2,-1,-4});
        
        System.out.println("Result: " + result);
    }
    
    public Set<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        var results = new HashSet<List<Integer>>();
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                var sum2 = nums[i] + nums[j];
                
                //search all after j+1
                var k = Arrays.binarySearch(nums, j+1, nums.length, -sum2); 
                
                if (k >= 0) {
                    var result = Arrays.asList(nums[i], nums[j], nums[k]);
                    results.add(result);
                    System.out.println("Found: " + result);
                }
            }
        }
        
        return results;
    }

}
