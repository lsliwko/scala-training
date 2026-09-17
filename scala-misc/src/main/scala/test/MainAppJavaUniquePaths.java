package test;

import java.util.concurrent.atomic.LongAdder;

public class MainAppJavaUniquePaths {

//There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
//
//Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
//
//The test cases are generated so that the answer will be less than or equal to 2 * 109.
//
//Example 1:
//
//Input: m = 3, n = 7
//Output: 28
//Example 2:
//
//Input: m = 3, n = 2
//Output: 3
//Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
//1. Right -> Down -> Down
//2. Down -> Down -> Right
//3. Down -> Right -> Down
    
    
    public static void main(String[] args) {
        MainAppJavaUniquePaths mainApp = new MainAppJavaUniquePaths();

        {
            var result = mainApp.uniquePaths(3, 7);
            System.out.println("RESULT: " + result);
        }
    }

    public int uniquePaths(int m, int n) {
        var adder =  new LongAdder();
        uniquePathsInner(adder,m-1,n-1);
        return adder.intValue();
    }

    private void uniquePathsInner(LongAdder adder, int remainingR, int remainingD) {

        if ((remainingD == 0) && (remainingR == 0)) {
            adder.increment();
            return;
        }
        
        //go right
        if (remainingR > 0) {
            uniquePathsInner(adder, remainingR-1, remainingD);
        }
        
        //go down
        if (remainingD > 0) {
            uniquePathsInner(adder, remainingR, remainingD-1);
        }
        
    }
    
}
