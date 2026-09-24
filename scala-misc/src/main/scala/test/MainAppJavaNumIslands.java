package test;

public class MainAppJavaNumIslands {

//Number of Islands
//Medium
//Topics
//premium lock icon
//Companies
//Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
//
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
// 
//
//Example 1:
//
//Input: grid = [
//  ['1','1','1','1','0'],
//  ['1','1','0','1','0'],
//  ['1','1','0','0','0'],
//  ['0','0','0','0','0']
//]
//Output: 1
//Example 2:
//
//Input: grid = [
//  ['1','1','0','0','0'],
//  ['1','1','0','0','0'],
//  ['0','0','1','0','0'],
//  ['0','0','0','1','1']
//]
//Output: 3

    public static void main(String[] args) {
        MainAppJavaNumIslands mainApp = new MainAppJavaNumIslands();

        {
            var grid = new char[][] {
                    {'1', '1', '1', '1', '0'},
                    {'1', '1', '0', '1', '0'},
                    {'1', '1', '0', '0', '0'},
                    {'0', '0', '0', '0', '0'}
            };
            var result = mainApp.numIslands(grid);
            System.out.println("RESULT: " + result);
        }
        {
            var grid = new char[][] {
                    {'1','1','0','0','0'},
                    {'1','1','0','0','0'},
                    {'0','0','1','0','0'},
                    {'0','0','0','1','1'}
            };
            var result = mainApp.numIslands(grid);
            System.out.println("RESULT: " + result);
        }
        {
            var grid = new char[][] {
                    {'0','1'}
            };
            var result = mainApp.numIslands(grid);
            System.out.println("RESULT: " + result);
        }
        {
            var grid = new char[][] {
                    {}
            };
            var result = mainApp.numIslands(grid);
            System.out.println("RESULT: " + result);
        }
    }
    
    private void fillIsland(int i, int j, char[][] grid, boolean[][] visited) {
        if (visited[i][j]) return;
        if (grid[i][j] == '0') return;

        visited[i][j] = true;
        //System.out.println("Visited: " + i + "-" + j);
        
        if (i > 0) fillIsland(i-1, j, grid, visited);
        if (i < grid.length - 1) fillIsland(i+1, j, grid, visited);
        if (j > 0) fillIsland(i, j-1, grid, visited);
        if (j < grid[0].length - 1) fillIsland(i, j+1, grid, visited);
    }

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        var count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j]) continue;
                if (grid[i][j] == '0') continue;

                //use flood algorithm
                fillIsland(i, j, grid, visited);
                count++;
            }
        }

        return count;
    }
    
}
