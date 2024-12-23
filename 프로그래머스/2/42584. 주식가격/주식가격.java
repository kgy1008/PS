import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<int[]> stack = new ArrayDeque<>();
        
        for (int i = 0; i<prices.length; i++) {
            while (!stack.isEmpty() && stack.peek()[1] > prices[i]) {
                int[] tmp = stack.pop();
                int idx = tmp[0];
                answer[idx] = i - idx;
            }
            stack.push(new int[]{i, prices[i]});
        }
        
        int lastIdx = prices.length - 1;
        while (!stack.isEmpty()) {
            int[] tmp = stack.pop();
            int idx = tmp[0];
            answer[idx] = lastIdx-idx;
        }
        
        return answer;
    }
}