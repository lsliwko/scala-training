package test;

import java.util.ArrayList;
import java.util.List;

public class MainAppJavaGenerateParethness {

//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//Example 1:
//Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
//Example 2:
//Input: n = 1
//Output: ["()"]
    
    public static void main(String[] args) {
        MainAppJavaGenerateParethness mainApp = new MainAppJavaGenerateParethness();
        {
            var result = mainApp.generateParenthesis(8);
            System.out.println(result);
        }
    }

    public List<String> generateParenthesis(int n) {
        var results = new ArrayList<String>();
        generateParenthesisInner("", results, n*2, n*2, 0, 0);
        return results;
    }

    public void generateParenthesisInner(String s, List<String> results, int depth, int totalDepth, int openCount, int closeCount) {
        if (openCount > totalDepth / 2) return;
        if (openCount < closeCount) return;

        if (depth == 0) {
            //System.out.println("Added: " + s);
            results.add(s);
        } else {
            generateParenthesisInner(s + "(", results, depth-1, totalDepth, openCount+1, closeCount);
            generateParenthesisInner(s + ")", results, depth-1, totalDepth, openCount, closeCount+1);
        }
    }
    
}
