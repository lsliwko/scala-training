package test;

public class MainAppJavaPalindrome {

    public static void main(String[] args) {
        MainAppJavaPalindrome mainApp = new MainAppJavaPalindrome();
        System.out.println(mainApp.longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        var longest = "";
        for (int i = 0; i <= s.length() - 1; i++) {
            for (int j = i+1; j <= s.length() - 1; j++) {
                var current = s.substring(i,j);
                
                System.out.println(current);
            }
        }
        
        return longest;
    }
}
