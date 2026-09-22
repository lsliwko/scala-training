package test;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainAppJava {

//You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
//
//Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:
//
//0 <= j <= nums[i] and
//i + j < n
//Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
//
// 
//
//Example 1:
//
//Input: nums = [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//Example 2:
//
//Input: nums = [2,3,0,1,4]
//Output: 2
    
    public static void main(String[] args) {
        MainAppJava mainApp = new MainAppJava();

        {
            var nums = new int[] {0};
            var result = mainApp.jump(nums);
            System.out.println("RESULT: " + result);
        }
        {
            var nums = new int[] {2,3,1,1,4};
            var result = mainApp.jump(nums);
            System.out.println("RESULT: " + result);
        }
        {
            var nums = new int[] {2,3,0,1,4};
            var result = mainApp.jump(nums);
            System.out.println("RESULT: " + result);
        }
        {
            var nums = new int[] {7,3,0,1,0};
            var result = mainApp.jump(nums);
            System.out.println("RESULT: " + result);
        }
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        //System.out.println(Arrays.toString(nums));
        
        int jumps = 0;
        int cur = nums.length - 1;
        while (true) {
            var bestJumpIndex = -1;
            for (int i = cur - 1; i >= 0; i--) {
                if (nums[i] >= cur - i) {
                    bestJumpIndex = i;
                }
            }

            cur = bestJumpIndex;
            
            jumps++;
            //System.out.println("JUMP TO: " + cur);
            
            if (cur == 0) break;
        }
        
        return jumps;
    }
    
}
