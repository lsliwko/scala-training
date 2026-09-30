package test;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class MainAppJava {

//You are given an integer array piles where piles[i] is the number of bananas in the ith pile. You are also given an integer h, which represents the number of hours you have to eat all the bananas.
//
//You may decide your bananas-per-hour eating rate of k. Each hour, you may choose a pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, you may finish eating the pile but you can not eat from another pile in the same hour.
//
//Return the minimum integer k such that you can eat all the bananas within h hours.
//
//Example 1:
//
//Input: piles = [1,4,3,2], h = 9
//
//Output: 2
//Explanation: With an eating rate of 2, you can eat the bananas in 6 hours. With an eating rate of 1, you would need 10 hours to eat all the bananas (which exceeds h=9), thus the minimum eating rate is 2.
//
//Example 2:
//
//Input: piles = [25,10,23,4], h = 4
//
//Output: 25
    
    public static void main(String[] args) {
        MainAppJava mainApp = new MainAppJava();

        {
            var result = mainApp.minEatingSpeed(new int[] {1,4,3,2}, 9);
            System.out.println(": " + result);
        }
        {
            var result = mainApp.minEatingSpeed(new int[] {25,10,23,4}, 4);
            System.out.println(": " + result);
        }
    }
    
    private int timeTakenToEatAll(int[] piles, int k) {
        int total = 0;
        for (int i = 0; i < piles.length; i++) {
            total += (int)Math.ceil((double) piles[i] / k);
        }
        return total;
    }

    public int minEatingSpeed(int[] piles, int h) {

        var max = Integer.MIN_VALUE;
        var min = 1;
        for (var pile : piles) {
            max = Math.max(max, pile);
        }
        
        while (min < max) {
            var k = (max + min) / 2;
            var timeTakenToEatAll = timeTakenToEatAll(piles, k);

            if (timeTakenToEatAll <= h) {
                max = k;       // decrease max
            } else {
                min = k + 1;   // increase min
            }
        }
        
        return min;
    }
    
}
