package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainAppJava {

//  Problem: 3Sum
//
//  Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
//  
//  i != j, i != k, j != k, and
//  nums[i] + nums[j] + nums[k] == 0
//
//  The result must not contain duplicate triplets (as sets of values, not indices).

    public static void main(String[] args) {
        MainAppJava mainApp = new MainAppJava();
        var result = mainApp.threeSum(new int[] {-1,0,1,2,-1,-4});
        
        System.out.println("Result: " + result);
    }
    
    public List<int[]> threeSum(int[] nums) {
        
        
        return null;
    }

}
