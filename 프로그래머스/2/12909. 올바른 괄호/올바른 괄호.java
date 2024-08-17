import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = false;

        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if (bracket == '(') {
                stack.push(bracket);
            }
            else {
                if (stack.isEmpty()){
                    return answer;
                }
                stack.pop();
            }
        }
        
        if (stack.isEmpty()) {
            answer = true;
        }

        return answer;
    }
}