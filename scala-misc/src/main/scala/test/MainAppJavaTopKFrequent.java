package test;
import java.util.*;

public class MainAppJavaTopKFrequent {

//Top K Frequent Elements
//Given an integer array nums and an integer k, return the k most frequent elements within the array.
//
//The test cases are generated such that the answer is always unique.
//
//You may return the output in any order.
//
//Example 1:
//
//Input: nums = [1,2,2,3,3,3], k = 2
//
//Output: [2,3]
//Example 2:
//
//Input: nums = [7,7], k = 1
//
//Output: [7]

    public static void main(String[] args) {
        MainAppJavaTopKFrequent mainApp = new MainAppJavaTopKFrequent();

        {
            var result = mainApp.topKFrequent(new int[] {2,2,3,1,1,1}, 2);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        {
            var result = mainApp.topKFrequent(new int[] {1,2,2,3,3,3}, 2);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
        {
            var result = mainApp.topKFrequent(new int[] {7,7}, 1);
            System.out.println("RESULT: " + Arrays.toString(result));
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        
        var freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            //freq.put(num, freq.getOrDefault(num, 0) + 1);
            freq.merge(num, 1, Integer::sum);
        }
        
        
//        return freq.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .limit(k)
//                .mapToInt(Map.Entry::getKey)
//                .toArray();


        // min-heap ordered by frequency (ascending)
        PriorityQueue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (var entry : freq.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll(); // evict the current smallest
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll().getKey();
        }
        return result;
    }
    
}
