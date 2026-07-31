package test;

public class MainAppJavaSearchInsertIndex {

    public static void main(String[] args) {
        MainAppJavaSearchInsertIndex mainApp = new MainAppJavaSearchInsertIndex();
        System.out.println(
                mainApp.searchInsert(new int[] {1,3,5,6}, 5)
        );
        System.out.println(
                mainApp.searchInsert(new int[] {1,3,5,6}, 2)
        );
        System.out.println(
                mainApp.searchInsert(new int[] {1,3,5,6}, 5)
        );
        System.out.println(
                mainApp.searchInsert(new int[] {1,3,5,6}, 5)
        );
        System.out.println(
                mainApp.searchInsert(new int[] {1,3,5,6}, 5)
        );
    }

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
            
            if (i == nums.length - 1) return nums.length;
        }
        return 0;
    }
}
