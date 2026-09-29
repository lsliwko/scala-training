package test;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class MainAppJava {

//You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.
//
//Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on a future day. If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.
//
//Example 1:
//
//Input: temperatures = [30,38,30,36,35,40,28]
//
//Output: [1,4,1,2,1,0,0]
//Example 2:
//
//Input: temperatures = [22,21,20]
//
//Output: [0,0,0]

    public static void main(String[] args) {
        MainAppJava mainApp = new MainAppJava();
        {
            var temperatures = new int[] {30,38,30,36,35,40,28};
            var result = mainApp.dailyTemperatures(temperatures);
            System.out.println(Arrays.toString(temperatures) + ": " + Arrays.toString(result));

            //correct: 1, 4, 1, 2, 1, 0, 0
        }

        {
            var temperatures = new int[] {22,21,20};
            var result = mainApp.dailyTemperatures(temperatures);
            System.out.println(Arrays.toString(temperatures) + ": " + Arrays.toString(result));
        }

        {
            var temperatures = new int[] {0};
            var result = mainApp.dailyTemperatures(temperatures);
            System.out.println(Arrays.toString(temperatures) + ": " + Arrays.toString(result));
        }

        {
            var temperatures = new int[] {};
            var result = mainApp.dailyTemperatures(temperatures);
            System.out.println(Arrays.toString(temperatures) + ": " + Arrays.toString(result));
        }
    }
    
    public int[] dailyTemperatures(int[] temperatures) {
        var stack = new ArrayDeque<DayWithTemperature>();
        
        var result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            var currentTemperature = temperatures[i];
            
            //take all days with lower temperature
            while (!stack.isEmpty() && currentTemperature > stack.peek().temperature()) {
                var prevDay = stack.pop();
                result[prevDay.index()] = i - prevDay.index();
            }

            //push index
            stack.push(new DayWithTemperature(i, currentTemperature));
        }

        return result;
    }
    
    record DayWithTemperature(
        int index,
        int temperature
    ) {}
    
    /*
    public int[] dailyTemperatures(int[] temperatures) {
        Map.Entry<Integer, Integer>[] temperaturesWithIndex = new Map.Entry[temperatures.length];
        
        for (int i = 0; i < temperatures.length; i++) {
            temperaturesWithIndex[i] = Map.entry(temperatures[i], i);
        }
        Arrays.sort(temperaturesWithIndex, Comparator.comparingInt(Map.Entry::getKey));
        
        System.out.println(Arrays.toString(temperaturesWithIndex));
        
        var result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                int tempThisDay = temperaturesWithIndex[i].getKey();
                int indexThisDay = temperaturesWithIndex[i].getValue();
                
                int tempNextDay = temperaturesWithIndex[j].getKey();
                int indexNextDay = temperaturesWithIndex[j].getValue();
                
                //same temperature
                if (tempThisDay == tempNextDay) continue;
                
                if (indexNextDay > indexThisDay) {
                    if (result[indexThisDay] > 0) {
                        result[indexThisDay] = Math.min(result[indexThisDay], indexNextDay - indexThisDay);
                    } else {
                        result[indexThisDay] = indexNextDay - indexThisDay;
                    }
                }
            }
        }
        
        return result;
    }
    
    */
    
}
