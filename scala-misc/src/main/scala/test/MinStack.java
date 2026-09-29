package test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class MinStack {

//Design a stack class that supports the push, pop, top, and getMin operations.
//
//MinStack() initializes the stack object.
//void push(int val) pushes the element val onto the stack.
//void pop() removes the element on the top of the stack.
//int top() gets the top element of the stack.
//int getMin() retrieves the minimum element in the stack.
//Each function should run in 
//O
//(
//1
//)
//O(1) time.
    
    static void main(String[] args) {
        var minStack = new MinStack();
        minStack.push(-1);
        minStack.push(3);
        minStack.push(-4);
        System.out.println("getMin:" + minStack.getMin());
        minStack.pop();
        System.out.println("getMin:" + minStack.getMin());
        System.out.println("top:" + minStack.top());
    }
    
    
    private final Deque<Map.Entry<Integer, Integer>> stack = new ArrayDeque<>();
    private int min = Integer.MAX_VALUE;
    
    public MinStack() {

    }

    public void push(int val) {
        stack.push(Map.entry(val, min));    //push current min
        min = Math.min(min, val);
    }

    public void pop() {
        var entry = stack.pop();
        min = entry.getValue();   // restore the min from before this push
    }

    public int top() {
        return stack.peek().getKey();   // read only, no removal
    }

    public int getMin() {
        return min;
    }
    
}
