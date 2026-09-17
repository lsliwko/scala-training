package test;

import java.util.HashSet;

public class MainAppJavaSudoku {

//Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
//
//Each row must contain the digits 1-9 without repetition.
//Each column must contain the digits 1-9 without repetition.
//Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
//Note:
//
//A Sudoku board (partially filled) could be valid but is not necessarily solvable.
//Only the filled cells need to be validated according to the mentioned rules.
    
    
    public static void main(String[] args) {
        MainAppJavaSudoku mainApp = new MainAppJavaSudoku();

        {
            var board = new char[][] {
                    new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };
            var result = mainApp.isValidSudoku(board);
            System.out.println("RESULT: " + result);
        }
    }

    public boolean isValidSudoku(char[][] board) {
        var set = new HashSet<Character>();
        
        //check vertical
        for (int x = 0; x < 9; x++) {
            set.clear();
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == '.') continue;
                if (!set.add(board[x][y])) {
                    return false;
                }
            }
        }

        //check horizontal
        for (int y = 0; y < 9; y++) {
            set.clear();
            for (int x = 0; x < 9; x++) {
                if (board[x][y] == '.') continue;
                if (!set.add(board[x][y])) {
                    return false;
                }
            }
        }

        //check squares
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                set.clear();
                for (int x = i * 3; x < i * 3 + 3; x++) {
                    for (int y = j * 3; y < j * 3 + 3; y++) {
                        if (board[x][y] == '.') continue;
                        if (!set.add(board[x][y])) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
}
