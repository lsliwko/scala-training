package test;

class Counters {
    long p1, p2, p3, p4, p5, p6, p7;
    int x = 0;
    long q1, q2, q3, q4, q5, q6, q7;
    int y = 0;
}

public class MinimalRace {
    public static void main(String[] args) throws InterruptedException {
        Counters c = new Counters();

        Runnable incrementer = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                c.x++;
                c.y++;
                
                if (c.x % 2 ==0) {
                    c.y++;
                }
            }
        };

        Thread t1 = new Thread(incrementer);
        Thread t2 = new Thread(incrementer);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("x=" + c.x + " y=" + c.y + " equal? " + (c.x == c.y));
    }
}