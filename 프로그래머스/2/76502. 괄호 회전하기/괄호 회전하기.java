import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            String rotated = s.substring(i) + s.substring(0, i);
            if (test(rotated.toCharArray())) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean test(char[] a) {
        Deque<Character> stack = new ArrayDeque<>(); 

        for (char bracket : a) {
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                stack.push(bracket);
            }
        
            else {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char test = stack.peek();
                
                if (test == '(' && bracket == ')' ||
                   test == '{' && bracket == '}' ||
                   test == '[' && bracket == ']') {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}
