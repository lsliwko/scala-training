package test;

public class MainAppJavaZigZag {

//Given a string s, return the longest palindromic substring in s.
//
//Example 1:
//Input: s = "babad"
//Output: "bab"
//Explanation: "aba" is also a valid answer.
//
//Example 2:
//Input: s = "cbbd"
//Output: "bb"
    
    public static void main(String[] args) {
        MainAppJavaZigZag mainApp = new MainAppJavaZigZag();
//        {
//            var nums = new int[] {2,7,11,15};
//            var result = mainApp.twoSum(nums, 9);
//            System.out.println(Arrays.toString(nums) + ": " + Arrays.toString(result));
//        }

        {
            var s = "PA";
            var result = mainApp.convert(s, 1);
            System.out.println(s + ": " + result);
        }
        {
            var s = "PAYPALISHIRING";
            var result = mainApp.convert(s, 3);
            System.out.println(s + ": " + result);
        }
//        {
//            var s = "PAYPALISHIRING";
//            var result = mainApp.convert(s, 4);
//            System.out.println(s + ": " + result);
//        }
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        
        StringBuilder[] results = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            results[i] = new StringBuilder();
        }
        
        boolean goingDown = true;
        int row = 0;
        for (char c : s.toCharArray()) {
            System.out.println("row: " + row);
            results[row].append(c);
            
            if (row == 0) goingDown = true;
            if (row == numRows - 1) goingDown = false;
            
            if (goingDown) row++;
            else row--;
        }

        var result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            result.append(results[i]);
        }
        return result.toString();
    }
    
}
