import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();
        
        int len = number.length();
        for (int i=0; i<len; i++) {
            int num = number.charAt(i) -'0';
            while (k>0 && !stack.isEmpty() && stack.peek() < num) {
                stack.pop();
                k--;
            } 
            stack.push(num);
        }
        
        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        String answer = sb.reverse().toString();
        return answer;
    }
}