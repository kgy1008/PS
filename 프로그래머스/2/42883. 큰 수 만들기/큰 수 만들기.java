import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i=0; i<number.length(); i++) {
            int c = number.charAt(i) - '0';
            while (!stack.isEmpty() && stack.peek() < c) {
                if (k > 0) {
                    stack.pop();
                    k--;
                } else {
                    break;
                }
            }
            stack.push(c);
        }
        
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}