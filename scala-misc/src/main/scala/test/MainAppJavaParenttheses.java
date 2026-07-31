package test;

public class MainAppJavaParenttheses {

    public static void main(String[] args) {
        MainAppJavaParenttheses mainApp = new MainAppJavaParenttheses();
        System.out.println(
            mainApp.removeOuterParentheses("()()")
        );
    }

    public String removeOuterParentheses(String s) {
        long depth = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(' && depth == 0) {
                //ignore
            } else if (s.charAt(i) == ')' && depth == 1) {
                //ignore 
            } else {
                output.append(s.charAt(i));
            }

                
            if (s.charAt(i) == '(') depth++;
            if (s.charAt(i) == ')') depth--;
            
        }
        
        return output.toString();
    }
}
