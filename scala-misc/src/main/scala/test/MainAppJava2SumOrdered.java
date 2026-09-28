package test;

import java.util.Arrays;

public class MainAppJava2SumOrdered {

//Given an array of integers numbers that is sorted in non-decreasing order.
//
//Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number target and index1 < index2. Note that index1 and index2 cannot be equal, therefore you may not use the same element twice.
//
//There will always be exactly one valid solution.
//
//Your solution must use 
//O
//(
//1
//)
//O(1) additional space.
//
//Example 1:
//
//Input: numbers = [1,2,3,4], target = 3
//
//Output: [1,2]
//Explanation:
//The sum of 1 and 2 is 3. Since we are assuming a 1-indexed array, index1 = 1, index2 = 2. We return [1, 2].

    public static void main(String[] args) {
        MainAppJava2SumOrdered mainApp = new MainAppJava2SumOrdered();
        
        {
            var result = mainApp.twoSum(new int[] {1,2,3,4}, 3);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        {
            var result = mainApp.twoSum(new int[] {0,1}, 1);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        {
            var result = mainApp.twoSum(new int[] {1,1}, 2);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        {
            var result = mainApp.twoSum(new int[] {1,1,2}, 3);
            System.out.println("RESULT: " + Arrays.toString(result));
        }

    }

    public int[] twoSum(int[] numbers, int target) {
        
        var left = 0;
        var right = numbers.length - 1;
        
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[] {left+1, right+1};
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                right--;
            }
            
        }

        return new int[] {left+1, right+1};
    }
    
}
