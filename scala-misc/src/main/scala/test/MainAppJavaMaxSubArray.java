package test;

public class MainAppJavaMaxSubArray {

//Given an integer array nums, find the subarray with the largest sum, and return its sum.
//
// Example 1:
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: The subarray [4,-1,2,1] has the largest sum 6.
//
// Example 2:
//Input: nums = [1]
//Output: 1
//Explanation: The subarray [1] has the largest sum 1.
//
// Example 3:
//Input: nums = [5,4,-1,7,8]
//Output: 23
//Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
    
    
    public static void main(String[] args) {
        MainAppJavaMaxSubArray mainApp = new MainAppJavaMaxSubArray();

        {
            var nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
            var result = mainApp.maxSubArray(nums);
            System.out.println("RESULT: " + result);
        }
        {
            var nums = new int[] {1};
            var result = mainApp.maxSubArray(nums);
            System.out.println("RESULT: " + result);
        }
        {
            var nums = new int[] {-1,-2};
            var result = mainApp.maxSubArray(nums);
            System.out.println("RESULT: " + result);
        }
        {
            var nums = new int[] {5,4,-1,7,8};
            var result = mainApp.maxSubArray(nums);
            System.out.println("RESULT: " + result);
        }
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        var tmpSum = 0;
        var tmpStart = 0;
        var tmpEnd = 0;
        
        var bestSum = nums[0];
        var bestStart = 0;
        var bestEnd = 1;
        for (var i = 0; i < nums.length; i++) {
            if (tmpSum + nums[i] > nums[i]) {
                //extend
                tmpSum = tmpSum + nums[i];
                tmpEnd = i;
            } else {
                //restart
                tmpStart = i;
                tmpEnd = i;
                tmpSum = nums[i];
            }
            
            if (tmpSum > bestSum) {
                bestSum = tmpSum;
                bestStart =  tmpStart;
                bestEnd = tmpEnd;
            }
        }

//        System.out.println("ARRAY:      " + Arrays.toString(nums));
//        System.out.println("BEST SUM:   " + bestSum);
//        System.out.println("BEST START: " + bestStart);
//        System.out.println("BEST END:   " + bestEnd);
        
        return (int)bestSum;
    }
    
}
