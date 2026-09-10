package test;

import java.util.Arrays;

public class MainAppJavaColorsCount {

    public static void main(String[] args) {
        MainAppJavaColorsCount mainApp = new MainAppJavaColorsCount();
        var nums = new int[] { 2,0,2,1,1,0 };
        mainApp.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortColors(int[] nums) {
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) red++;
            else if (nums[i] == 1) green++;
            else if (nums[i] == 2) blue++;
        }
        
        var j = 0;
        var c = 0;
        while (c<red) {
            c++;
            nums[j++] = 0;
        }
        c = 0;
        while (c<green) {
            c++;
            nums[j++] = 1;
        }
        c = 0;
        while (c<blue) {
            c++;
            nums[j++] = 2;
        }
        
    }
}
