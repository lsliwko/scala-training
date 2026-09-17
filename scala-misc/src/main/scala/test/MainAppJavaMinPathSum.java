package test;

public class MainAppJavaMinPathSum {

//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
//
//Note: You can only move either down or right at any point in time.
//
// 
//
//Example 1:
//
//
//Input: grid = [
// [1,3,1],
// [1,5,1],
// [4,2,1]
// ]
//Output: 7
//Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
//Example 2:
//
//Input: grid = [[1,2,3],[4,5,6]]
//Output: 12
    
    
    public static void main(String[] args) {
        MainAppJavaMinPathSum mainApp = new MainAppJavaMinPathSum();

        {
            var grid =  new int[][] {
                    {1,3,1},
                    {1,5,1},
                    {4,2,1},
            };
            var result = mainApp.minPathSum(grid);
            System.out.println("RESULT: " + result);
        }
    }

    public int minPathSum(int[][] grid) {

        int sum;
        
        //calculate first row
        sum = 0;
        for (int y = 0; y < grid[0].length; y++) {
            sum = sum + grid[0][y];
            grid[0][y] = sum;
        }
        
        //calculate column
        sum = 0;
        for (int x = 0; x < grid.length; x++) {
            sum = sum + grid[x][0];
            grid[x][0] = sum;
        }

        for (int x = 1; x < grid.length; x++) {
            for (int y = 1; y < grid[0].length; y++) {
                grid[x][y] = Math.min(grid[x - 1][y], grid[x][y - 1]) + grid[x][y];
            }
        }
        
        return grid[grid.length-1][grid[0].length-1];
    }
    
}
