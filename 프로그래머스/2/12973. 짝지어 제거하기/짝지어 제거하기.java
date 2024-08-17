import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (i == 0 || stack.isEmpty()) {
                stack.push(temp);
            }
            else {
                if (stack.peek() == temp) {
                    stack.pop();
                }
                else {
                    stack.push(temp);
                }
            }
        }
        
        if (stack.isEmpty()) {
            answer = 1;
        }
        return answer;
    }
}