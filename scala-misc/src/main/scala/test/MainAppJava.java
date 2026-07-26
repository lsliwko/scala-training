package test;

public class MainAppJava {

    public static void main(String[] args) {
        int x = -2147483648;
        MainAppJava mainApp = new MainAppJava();
        System.out.println(
            mainApp.reverse(x)
        );
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int digit = x % 10;
            x = x / 10;

            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && x > 7)) {
                return 0;
            }

            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && x < -8)) {
                return 0;
            }

            rev = rev * 10 + digit;
        }

        return rev;
    }
}
