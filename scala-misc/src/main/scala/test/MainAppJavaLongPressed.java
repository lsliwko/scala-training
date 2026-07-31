package test;

import java.util.ArrayList;
import java.util.List;

public class MainAppJavaLongPressed {

    public static void main(String[] args) {
        MainAppJavaLongPressed mainApp = new MainAppJavaLongPressed();
        System.out.println(mainApp.isLongPressedName(
                "alex", 
                "aaleexa"
        ));

//        System.out.println(mainApp.splitToWords(
//                "saeed"
//        ));
//
//        System.out.println(mainApp.splitToWords(
//                "saeed"
//        ));
    }

    public boolean isLongPressedName(String name, String typed) {
        var nameWords = splitToWords(name);
        var typedWords = splitToWords(typed);
        
        for (int i=0;i<nameWords.size();i++) {
            //System.out.println("Checking " + typedWords.get(i) + " (typed) vs " + nameWords.get(i));
            
            if (nameWords.get(i).charAt(0) != typedWords.get(i).charAt(0)) return false;
            
            if (typedWords.get(i).length() >= nameWords.get(i).length()) {
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    private List<String> splitToWords(String text) {
        var output = new ArrayList<String>();
        char lastChar = 0;
        StringBuilder lastWord = null;
        for (int i=0;i<text.length();i++) {
            char thisChar = text.charAt(i);
            if (lastChar == thisChar) {
                lastWord.append(lastChar);
            } else {
                if (lastWord != null) output.add(lastWord.toString());
                lastWord = new StringBuilder();
                lastWord.append(thisChar);
                lastChar = thisChar;
            }
            
            if (i == text.length() - 1) {
                output.add(lastWord.toString());
            }
        }
        return output;
    }
    
}
