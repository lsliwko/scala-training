package test;

public class MainAppJavaCountPrimes {

//Given an integer n, return the number of prime numbers that are strictly less than n.
//
//Example 1:
//Input: n = 10
//Output: 4
//Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
//
//Example 2:
//Input: n = 0
//Output: 0
//
//Example 3:
//Input: n = 1
//Output: 0

    public static void main(String[] args) {
        MainAppJavaCountPrimes mainApp = new MainAppJavaCountPrimes();

        {
            var result = mainApp.countPrimes(10);
            System.out.println("RESULT: " + result);
        }
        {
            var result = mainApp.countPrimes(0);
            System.out.println("RESULT: " + result);
        }
        {
            var result = mainApp.countPrimes(1);
            System.out.println("RESULT: " + result);
        }
        {
            var result = mainApp.countPrimes(3);
            System.out.println("RESULT: " + result);
        }
    }

    public int countPrimes(int n) {
        var nonPrimes = new boolean[n];
        var count = 0;
        for (var i = 2; i < n; i++) {
            if (nonPrimes[i]) continue;
            count++;                                   // i is prime
            for (long j = (long) 2 * i; j < n; j += i) {
                nonPrimes[(int) j] = true;             // mark composites
            }
        }
        return count;
    }
    
}
