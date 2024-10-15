import java.util.*;

class Solution {
    boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty()) return false;
                else {
                    if (stack.peek() == '(') {
                        stack.pop();
                    }
                    else return false;
                }
            }
            else {
                stack.push(c);
            }
        }
        
        if (stack.isEmpty()) return true;
        else return false;
    }
}