package test;

import java.util.ArrayDeque;

public class MainAppJavaValidParetness {

//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//An input string is valid if:
//
//Open brackets must be closed by the same type of brackets.
//Open brackets must be closed in the correct order.
//Every close bracket has a corresponding open bracket of the same type.
    
    public static void main(String[] args) {
        MainAppJavaValidParetness mainApp = new MainAppJavaValidParetness();
//        {
//            var nums = new int[] {2,7,11,15};
//            var result = mainApp.twoSum(nums, 9);
//            System.out.println(Arrays.toString(nums) + ": " + Arrays.toString(result));
//        }

        {
            var s = "()";
            var result = mainApp.isValid(s);
            System.out.println(s + ": " + result);
        }
        {
            var s = "()[]{}";
            var result = mainApp.isValid(s);
            System.out.println(s + ": " + result);
        }
        {
            var s = "(]";
            var result = mainApp.isValid(s);
            System.out.println(s + ": " + result);
        }
        {
            var s = "([])";
            var result = mainApp.isValid(s);
            System.out.println(s + ": " + result);
        }
        {
            var s = "([)]";
            var result = mainApp.isValid(s);
            System.out.println(s + ": " + result);
        }
        {
            var s = "(";
            var result = mainApp.isValid(s);
            System.out.println(s + ": " + result);
        }
        {
            var s = "";
            var result = mainApp.isValid(s);
            System.out.println(s + ": " + result);
        }
    }

    public boolean isValid(String s) {
        var stack = new ArrayDeque<Character>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                
                if ((c == ')') && (stack.pop() != '(')) return false;
                if ((c == ']') && (stack.pop() != '[')) return false;
                if ((c == '}') && (stack.pop() != '{')) return false;
            }
            
        }
        
        return stack.isEmpty();
    }
    
}
