import java.util.*;

class Solution {
    public int solution(String s) {
        String[] cmd = s.split(" ");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for (String c : cmd) {
            if (c.equals("Z")) {
                stack.pop();
            }
            else {
                stack.push(Integer.parseInt(c));
            }
        }
        
        int answer = 0;
        while(!stack.isEmpty()) {
            answer += stack.pop();
        }
        return answer;
    }
}