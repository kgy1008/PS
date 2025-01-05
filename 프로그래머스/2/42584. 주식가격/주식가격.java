import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int[] answer = new int[prices.length];
        
        for (int i=1; i<prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            stack.push(i);
        }
        
        int maxIdx = prices.length-1;
      
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = maxIdx - idx;
        }
        
        return answer;
    }
}