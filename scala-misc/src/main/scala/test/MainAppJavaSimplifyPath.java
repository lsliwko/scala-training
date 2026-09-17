package test;

import java.util.ArrayDeque;

public class MainAppJavaSimplifyPath {

//You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
//
//The rules of a Unix-style file system are as follows:
//
//A single period '.' represents the current directory.
//A double period '..' represents the previous/parent directory.
//Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
//Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
//The simplified canonical path should follow these rules:
//
//The path must start with a single slash '/'.
//Directories within the path must be separated by exactly one slash '/'.
//The path must not end with a slash '/', unless it is the root directory.
//The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
//Return the simplified canonical path.
    
    
    public static void main(String[] args) {
        MainAppJavaSimplifyPath mainApp = new MainAppJavaSimplifyPath();

        {
            var path = "/home/";
            var result = mainApp.simplifyPath(path);
            System.out.println(path + " -> " + result);
        }
        {
            var path = "/home//foo/";
            var result = mainApp.simplifyPath(path);
            System.out.println(path + " -> " + result);
        }
        {
            var path = "/../";
            var result = mainApp.simplifyPath(path);
            System.out.println(path + " -> " + result);
        }
        {
            var path = "/home/user/Documents/../Pictures";
            var result = mainApp.simplifyPath(path);
            System.out.println(path + " -> " + result);
        }
        {
            var path = "/.../a/../b/c/../d/./";
            var result = mainApp.simplifyPath(path);
            System.out.println(path + " -> " + result);
        }
        {
            var path = "/a/./b/../../c/";   //"/c"
            var result = mainApp.simplifyPath(path);
            System.out.println(path + " -> " + result);
        }
    }

    public String simplifyPath(String path) {
        
        var stack = new ArrayDeque<String>();
        
        var pathTmp = path.replace("/./","/");

        for (var pathElem : pathTmp.split("/")) {
            stack.push(pathElem);
        }
        
        var result = new StringBuilder();
        var ignoreNext = 0;
        while (!stack.isEmpty()) {
            var pathElem = stack.pop();

            if (pathElem.isEmpty()) continue;
            if (pathElem.equals(".")) continue;
            
            if (pathElem.equals("..")) {
                ignoreNext++;
                continue;
            }
            
            if (ignoreNext > 0) {
                ignoreNext--;
                continue;
            }
            
            result.insert(0, "/" + pathElem);
        }
        
        if (result.isEmpty()) return "/";
        return result.toString();
    }
    
}
