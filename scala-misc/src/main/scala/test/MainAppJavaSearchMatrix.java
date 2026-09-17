package test;

public class MainAppJavaSearchMatrix {

//You are given an m x n integer matrix with the following two properties:
//
//Each row is sorted in non-decreasing order.
//The first integer of each row is greater than the last integer of the previous row.
//Given an integer target, return true if target is in matrix or false otherwise.
//
//You must write a solution in O(log(m * n)) time complexity.
//
//Example 1:
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//Output: true
//
//Example 2:
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//Output: false
    
    
    public static void main(String[] args) {
        MainAppJavaSearchMatrix mainApp = new MainAppJavaSearchMatrix();

        {
            var matrix = new int[][] {
                { 1, 3, 5, 7},
                {10,11,16,20},
                {23,30,34,60}
            };
            var result = mainApp.searchMatrix(matrix, 11);
            System.out.println("RESULT: " + result);
        }
    }
    
    private int valueAt(int[][] matrix, int mid) {
        var x = mid / matrix[0].length;
        var y = mid % matrix[0].length;
        return matrix[x][y];
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        int left = 0;
        int right = matrix[0].length * matrix.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            int value = valueAt(matrix, mid);
            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else  {
                right = mid;
            }
        }
        
        return false;
    }
    
}
