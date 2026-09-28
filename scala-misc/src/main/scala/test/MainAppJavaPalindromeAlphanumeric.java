package test;

public class MainAppJavaPalindromeAlphanumeric {

//Given a string s, return true if it is a palindrome, otherwise return false.
//
//A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.
//
//Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).
//
//Example 1:
//
//Input: s = "Was it a car or a cat I saw?"
//
//Output: true
//Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.
//
//Example 2:
//
//Input: s = "tab a cat"
//
//Output: false

    public static void main(String[] args) {
        MainAppJavaPalindromeAlphanumeric mainApp = new MainAppJavaPalindromeAlphanumeric();
        
//        {
//            var result = mainApp.isPalindrome("Was it a car or a cat I saw?");
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var result = mainApp.isPalindrome("tab a cat");
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var result = mainApp.isPalindrome("a");
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var result = mainApp.isPalindrome("aa");
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var result = mainApp.isPalindrome(" ");
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var result = mainApp.isPalindrome("  ");
//            System.out.println("RESULT: " + result);
//        }
//        {
//            var result = mainApp.isPalindrome("");
//            System.out.println("RESULT: " + result);
//        }
        {
            var result = mainApp.isPalindrome("No lemon, no melon");
            System.out.println("RESULT: " + result);
        }

    }

    private boolean isAlphanumeric(char c) {
        return 
                (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            while (left < right && !isAlphanumeric(s.charAt(left))) left++;
            while (left < right && !isAlphanumeric(s.charAt(right))) right--;

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
    
}
