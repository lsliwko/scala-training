package test;

import java.util.ArrayDeque;
import java.util.Arrays;

public class MainAppJavaPolishNotation {

//You are given an array of strings tokens that represents a valid arithmetic expression in Reverse Polish Notation.
//
//Return the integer that represents the evaluation of the expression.
//
//The operands may be integers or the results of other operations.
//The operators include '+', '-', '*', and '/'.
//Assume that division between integers always truncates toward zero.
//Example 1:
//
//Input: tokens = ["1","2","+","3","*","4","-"]
//
//Output: 5
//
//Explanation: ((1 + 2) * 3) - 4 = 5

    public static void main(String[] args) {
        MainAppJavaPolishNotation mainApp = new MainAppJavaPolishNotation();

        {
            var tokens = new String[] {"1","2","+"};
            var result = mainApp.evalRPN(tokens);
            System.out.println(Arrays.toString(tokens) + ": " + result);
        }
        {
            var tokens = new String[] {"1","2","+","3","*","4","-"};
            var result = mainApp.evalRPN(tokens);
            System.out.println(Arrays.toString(tokens) + ": " + result);
        }
        {
            var tokens = new String[] {"1","10","/"};
            var result = mainApp.evalRPN(tokens);
            System.out.println(Arrays.toString(tokens) + ": " + result);
        }
        {
            var tokens = new String[] {"-1","10","/"};
            var result = mainApp.evalRPN(tokens);
            System.out.println(Arrays.toString(tokens) + ": " + result);
        }
        {
            var tokens = new String[] {"1"};
            var result = mainApp.evalRPN(tokens);
            System.out.println(Arrays.toString(tokens) + ": " + result);
        }
        {
            var tokens = new String[] {};
            var result = mainApp.evalRPN(tokens);
            System.out.println(Arrays.toString(tokens) + ": " + result);
        }
    }

    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) return 0;
        
        var stack = new ArrayDeque<Integer>();
        
        for (String token : tokens) {
            //'+', '-', '*', and '/'
            if (token.equals("+")) {
                var second = stack.pop();
                var first = stack.pop();
                stack.push(first + second);
            } else if (token.equals("-")) {
                var second = stack.pop();
                var first = stack.pop();
                stack.push(first - second);
            } else if (token.equals("*")) {
                var second = stack.pop();
                var first = stack.pop();
                stack.push(first * second);
            } else if (token.equals("/")) {
                var second = stack.pop();
                var first = stack.pop();
                stack.push(first / second);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.peek();
    }
    
}
