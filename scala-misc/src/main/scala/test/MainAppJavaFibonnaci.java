package test;

public class MainAppJavaFibonnaci {

    public static void main(String[] args) {
        MainAppJavaFibonnaci mainApp = new MainAppJavaFibonnaci();
        System.out.println(mainApp.fib(2));
        System.out.println(mainApp.fib(3));
        System.out.println(mainApp.fib(4));
        System.out.println(mainApp.fib(5));
        System.out.println(mainApp.fib(6));
        System.out.println(mainApp.fib(7));
    }
    
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int current = 1;
        int prev = 0;

        for (int i = 0; i < n-1; i++) {
            int temp = prev + current;
            prev = current;
            current = temp;
        }
        
        return current;
    }
    
}
