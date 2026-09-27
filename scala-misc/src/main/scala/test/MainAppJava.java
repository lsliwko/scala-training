package test;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class MainAppJava {

//Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
//
//Each product is guaranteed to fit in a 32-bit integer.
//
//Follow-up: Could you solve it in 
//O
//(
//n
//)
//O(n) time without using the division operation?
//
//Example 1:
//
//Input: nums = [1,2,4,6]
//
//Output: [48,24,12,8]
//Example 2:
//
//Input: nums = [-1,0,1,2,3]
//
//Output: [0,-6,0,0,0]

    public static void main(String[] args) {
        MainAppJava mainApp = new MainAppJava();

//        {
//            var nums = new int[] {1,2,4,6};
//            var result = mainApp.isValidSudoku(nums);
//            System.out.println("RESULT: " + Arrays.toString(result));
//        }
//        {
//            var nums = new int[] {-1,0,1,2,3};
//            var result = mainApp.productExceptSelf(nums);
//            System.out.println("RESULT: " + Arrays.toString(result));
//        }
//        {
//            var nums = new int[] {1,0,-1};
//            var result = mainApp.productExceptSelf(nums);
//            System.out.println("RESULT: " + Arrays.toString(result));
//        }
        
    }
    
    
    private boolean checkUniqueNumbers(char[] arr) {
        var set = new HashSet<Character>();
        for (char c : arr) {
            if (c == '.') continue;
            if (!set.add(c)) return false;
        }
        return true;
    }


    public boolean isValidSudoku(char[][] board) {

        //check columns
        for (int i = 0; i < 9; i++) {
            if (!checkUniqueNumbers(board[i])) return false;
        }
        
        //check rows
        for (int i = 0; i < 9; i++) {
            var row = new char[] {
                    board[0][i],
                    board[1][i],
                    board[2][i],
                    board[3][i],
                    board[4][i],
                    board[5][i],
                    board[6][i],
                    board[7][i],
                    board[8][i]
            };
            
            if (!checkUniqueNumbers(row)) return false;
        }
        
        //check squares
        if (!checkUniqueNumbers(
                new char[] {
                        board[0][0], board[1][0], board[2][0],
                        board[0][1], board[1][1], board[2][1],
                        board[0][2], board[1][2], board[2][2]
                }
        )) return false;

        if (!checkUniqueNumbers(
                new char[] {
                        board[3][0], board[4][0], board[5][0],
                        board[3][1], board[4][1], board[5][1],
                        board[3][2], board[4][2], board[5][2]
                }
        )) return false;

        if (!checkUniqueNumbers(
                new char[] {
                        board[6][0], board[7][0], board[8][0],
                        board[6][1], board[7][1], board[8][1],
                        board[6][2], board[7][2], board[8][2]
                }
        )) return false;


        if (!checkUniqueNumbers(
                new char[] {
                        board[0][3], board[1][3], board[2][3],
                        board[0][4], board[1][4], board[2][4],
                        board[0][5], board[1][5], board[2][5]
                }
        )) return false;

        if (!checkUniqueNumbers(
                new char[] {
                        board[3][3], board[4][3], board[5][3],
                        board[3][4], board[4][4], board[5][4],
                        board[3][5], board[4][5], board[5][5]
                }
        )) return false;

        if (!checkUniqueNumbers(
                new char[] {
                        board[6][3], board[7][3], board[8][3],
                        board[6][4], board[7][4], board[8][4],
                        board[6][5], board[7][5], board[8][5]
                }
        )) return false;


        if (!checkUniqueNumbers(
                new char[] {
                        board[0][6], board[1][6], board[2][6],
                        board[0][7], board[1][7], board[2][7],
                        board[0][8], board[1][8], board[2][8]
                }
        )) return false;

        if (!checkUniqueNumbers(
                new char[] {
                        board[3][6], board[4][6], board[5][6],
                        board[3][7], board[4][7], board[5][7],
                        board[3][8], board[4][8], board[5][8]
                }
        )) return false;

        if (!checkUniqueNumbers(
                new char[] {
                        board[6][6], board[7][6], board[8][6],
                        board[6][7], board[7][7], board[8][7],
                        board[6][8], board[7][8], board[8][8]
                }
        )) return false;
        
        return true;
    }
    
}
