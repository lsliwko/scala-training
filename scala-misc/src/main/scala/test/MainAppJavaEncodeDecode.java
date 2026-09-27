package test;

import java.util.ArrayList;
import java.util.List;

public class MainAppJavaEncodeDecode {

//Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
//
//Machine 1 (sender) has the function:
//
//String encode(List<String> strs) {
//    // ... your code
//    return encoded_string;
//}
//Machine 2 (receiver) has the function:
//
//List<String> decode(String encoded_string) {
//    // ... your code
//    return decoded_strs;
//}
//So Machine 1 does:
//
//String encoded_string = encode(strs);
//and Machine 2 does:
//
//List<String> decoded_strs = decode(encoded_string);
//decoded_strs in Machine 2 should be the same as the input strs in Machine 1.
//
//Implement the encode and decode methods.

    public static void main(String[] args) {
        MainAppJavaEncodeDecode mainApp = new MainAppJavaEncodeDecode();

//        {
//            var result = mainApp.encode(List.of());
//            System.out.println("RESULT: " + result);
//
//            var result2 = mainApp.decode(result);
//            System.out.println("RESULT2: " + result2);
//        }

//        {
//            var result = mainApp.encode(List.of(
//                    "Hello","World"
//            ));
//            System.out.println("RESULT: " + result);
//
//            var result2 = mainApp.decode(result);
//            System.out.println("RESULT2: " + result2);
//        }
        {
            var result = mainApp.encode(List.of(
                    "Hello","","World"
            ));
            System.out.println("RESULT: " + result);

            var result2 = mainApp.decode(result);
            System.out.println("RESULT2: " + result2);
        }
        {
            var result = mainApp.encode(List.of(
                    ""
            ));
            System.out.println("RESULT: " + result);

            var result2 = mainApp.decode(result);
            System.out.println("RESULT2: " + result2);
        }
        
    }

    public String encode(List<String> strs) {

        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str.length() + "#" + str);
        }

        return sb.toString();
    }

    public List<String> decode(String str) {
        if (str.isEmpty()) return List.of();

        var result = new ArrayList<String>();

        var pos = 0;
        while (pos < str.length()) {
            var numberIndex = str.indexOf('#', pos);
            if (numberIndex == -1) break;

            var lengthStr = str.substring(pos, numberIndex);
            int length = Integer.parseInt(lengthStr);

            var startPos = numberIndex + 1;
            var endPos = numberIndex + 1 + length;
            var strTmp = str.substring(startPos, endPos);

            pos = endPos;

            result.add(strTmp);
        }


        return result;
    }
    
}
